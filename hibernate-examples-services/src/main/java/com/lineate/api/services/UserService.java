package com.lineate.api.services;

import com.lineate.api.core.domain.app.User;
import com.lineate.api.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    User addUser(User user);

    List<UserResponse> getAllUsers();

    User getUserByEmail(String email);

    boolean isEmailExist(String email);
}
