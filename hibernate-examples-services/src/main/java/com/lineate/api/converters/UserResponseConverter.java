package com.lineate.api.converters;

import com.lineate.api.core.domain.app.User;
import com.lineate.api.dto.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserResponseConverter {
    public List<UserResponse> getFromUserList(List<User> userList) {
        return userList
            .stream()
            .map(user -> new UserResponse(user.getId(), user.getEmail(), user.getName()))
            .collect(Collectors.toList());
    }

    public UserResponse getFromUser(User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getName());
    }
}
