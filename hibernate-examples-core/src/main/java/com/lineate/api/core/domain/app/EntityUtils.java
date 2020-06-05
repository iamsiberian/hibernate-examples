package com.lineate.api.core.domain.app;

public class EntityUtils {
    public static User createUser(String email, String password, String name, UserType userType) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setUserType(userType);
        return user;
    }
}
