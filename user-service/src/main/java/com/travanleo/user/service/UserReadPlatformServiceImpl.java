package com.travanleo.user.service;

import com.travanleo.user.data.UserData;
import com.travanleo.user.domain.JpaUserRepository;
import com.travanleo.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserReadPlatformServiceImpl implements UserReadPlatformService {

    private final JpaUserRepository userRepository;

    private final UserMapper userMapper = new UserMapper();

    @Autowired
    public UserReadPlatformServiceImpl(final JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserData> retrieveAll() {
        List<User> users = userRepository.findAll();
        return userMapper.map(users);
    }

    private static final class UserMapper {

        public UserMapper() {

        }

        public UserData map(final User user) {
            return UserData.newInstance(user.getFirstName(), user.getLastName(),
                    user.getMobile(), user.getAge(), user.getEmail());
        }

        public List<UserData> map(final List<User> users) {
            final List<UserData> userDataList = new ArrayList<>();
            for(final User user: users) {
                userDataList.add(map(user));
            }
            return userDataList;
        }
    }
}
