package com.travanleo.user.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class JpaUserRepositoryWrapper {

    private JpaUserRepository userRepository;

    @Autowired
    public JpaUserRepositoryWrapper(final JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly=true)
    public User findOneWithNotFoundDetection(final Long userId) {
        final Optional<User> user = this.userRepository.findById(userId);
        if (user.isEmpty()) { throw new RuntimeException("User Not Found"); }
        return user.get();
    }
}
