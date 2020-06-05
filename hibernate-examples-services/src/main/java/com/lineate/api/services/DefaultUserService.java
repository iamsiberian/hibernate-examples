package com.lineate.api.services;

import com.lineate.api.converters.UserResponseConverter;
import com.lineate.api.core.domain.app.User;
import com.lineate.api.core.repositories.app.UserRepository;
import com.lineate.api.dto.response.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultUserService implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUserService.class);
    private final UserRepository userRepository;
    private final UserResponseConverter userResponseConverter;

    @Autowired
    public DefaultUserService(
        final UserRepository userRepository,
        final UserResponseConverter userResponseConverter
    ) {
        this.userRepository = userRepository;
        this.userResponseConverter = userResponseConverter;
    }

    @Transactional
    @Override
    public User addUser(User user) {
        user = userRepository.save(user);
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserResponse> getAllUsers() {
        return userResponseConverter.getFromUserList(userRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isEmailExist(String email) {
        return userRepository.checkEmail(email) != null;
    }
}
