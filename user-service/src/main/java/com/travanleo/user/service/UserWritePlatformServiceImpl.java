package com.travanleo.user.service;

import com.travanleo.core.api.JsonCommand;
import com.travanleo.core.data.CommandProcessingResult;
import com.travanleo.core.data.CommandProcessingResultBuilder;
import com.travanleo.user.api.UserApiConstants;
import com.travanleo.user.data.UserDataValidator;
import com.travanleo.user.domain.JpaUserRepository;
import com.travanleo.user.domain.JpaUserRepositoryWrapper;
import com.travanleo.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
            userDataValidator.validateForCreate(command.toString());
            String firstName = command.stringValueOfParameterNamed(UserApiConstants.FIRST_NAME);
            String lastName = command.stringValueOfParameterNamed(UserApiConstants.LAST_NAME);
            Long mobile = command.longValueOfParameterNamed(UserApiConstants.MOBILE);
            Integer age = command.integerValueOfParameterNamed(UserApiConstants.ageParamName);
            String email = command.stringValueOfParameterNamed(UserApiConstants.email);

            user.updateFirstName(firstName);
            user.updateLastName(lastName);
            userRepository.saveAndFlush(user);
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
    public CommandProcessingResult deleteUser(final Long userId, final JsonCommand command) {
        return CommandProcessingResult.empty();
    }
}
