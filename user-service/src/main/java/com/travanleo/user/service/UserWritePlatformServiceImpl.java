package com.travanleo.user.service;

import com.travanleo.user.api.JsonCommand;
import com.travanleo.user.api.UserApiConstants;
import com.travanleo.user.data.CommandProcessingResult;
import com.travanleo.user.data.CommandProcessingResultBuilder;
import com.travanleo.user.data.UserDataValidator;
import com.travanleo.user.domain.JpaUserRepository;
import com.travanleo.user.domain.JpaUserRepositoryWrapper;
import com.travanleo.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserWritePlatformServiceImpl implements UserWritePlatformService {

    private UserDataValidator userDataValidator;

    private JpaUserRepository userRepository;

    private JpaUserRepositoryWrapper userRepositoryWrapper;

    @Autowired
    public UserWritePlatformServiceImpl(final UserDataValidator userDataValidator,
                                        final JpaUserRepository userRepository,
                                        final JpaUserRepositoryWrapper userRepositoryWrapper) {
        this.userDataValidator = userDataValidator;
        this.userRepository = userRepository;
        this.userRepositoryWrapper = userRepositoryWrapper;
    }

    @Override
    public CommandProcessingResult createUser(final JsonCommand command) {
        try {
            // validate
            userDataValidator.validateForCreate(command.json());
            String firstName = command.stringValueOfParameterNamed(UserApiConstants.FIRST_NAME);
            String lastName = command.stringValueOfParameterNamed(UserApiConstants.LAST_NAME);
            Long mobile = command.longValueOfParameterNamed(UserApiConstants.MOBILE);
            Integer age = command.integerValueOfParameterNamed(UserApiConstants.ageParamName);
            String email = command.stringValueOfParameterNamed(UserApiConstants.email);

            User user = new User(firstName, lastName, mobile, age, email);
            userRepository.save(user);
            return new CommandProcessingResultBuilder()
                    .withCommandId(command.commandId())
                    .withUserId(user.getId())
                    .withEntityId(user.getId())
                    .build();
        } catch (Exception e) {
            return CommandProcessingResult.empty();
        }
    }

    @Override
    public CommandProcessingResult updateUser(final Long userId, final JsonCommand command) {
        try {
            final User user = userRepositoryWrapper.findOneWithNotFoundDetection(userId);
            // validate
            userDataValidator.validateForUpdate(command.json());

            final Map<String, Object> changes = user.update(command);
            if(changes.containsKey(UserApiConstants.FIRST_NAME)) {
                String firstName = command.stringValueOfParameterNamed(UserApiConstants.FIRST_NAME);
                user.updateFirstName(firstName);
            }
            if(changes.containsKey(UserApiConstants.LAST_NAME)) {
                String lastName = command.stringValueOfParameterNamed(UserApiConstants.LAST_NAME);
                user.updateLastName(lastName);
            }
            if(changes.containsKey(UserApiConstants.email)) {
                String email = command.stringValueOfParameterNamed(UserApiConstants.email);
                user.updateEmail(email);
            }
            if(changes.containsKey(UserApiConstants.MOBILE)) {
                Long mobile = command.longValueOfParameterNamed(UserApiConstants.MOBILE);
                user.updateMobile(mobile);
            }
            if(changes.containsKey(UserApiConstants.ageParamName)) {
                Integer age = command.integerValueOfParameterNamed(UserApiConstants.ageParamName);
                user.updateAge(age);
            }

            if (!changes.isEmpty()) {
                this.userRepository.saveAndFlush(user);
            }
            return new CommandProcessingResultBuilder()
                    .withCommandId(command.commandId())
                    .withUserId(user.getId())
                    .withEntityId(user.getId())
                    .with(changes)
                    .build();
        } catch (Exception e) {
            return CommandProcessingResult.empty();
        }
    }

    @Override
    public CommandProcessingResult deleteUser(final Long userId, final JsonCommand command) {
        final User user = userRepositoryWrapper.findOneWithNotFoundDetection(userId);
        this.userRepository.delete(user);
        this.userRepository.flush();
        return new CommandProcessingResultBuilder()
                .withUserId(userId)
                .withEntityId(userId)
                .build();
    }
}
