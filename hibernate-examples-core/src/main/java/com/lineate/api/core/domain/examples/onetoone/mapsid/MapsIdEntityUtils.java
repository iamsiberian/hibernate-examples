package com.lineate.api.core.domain.examples.onetoone.mapsid;

public class MapsIdEntityUtils {
    public static User createUser(String email, String password, String name, UserType userType) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setUserType(userType);
        return user;
    }

    public static UserProfile createUserProfile(String profileInfo, User user) {
        UserProfile userProfile = new UserProfile();
        userProfile.setProfileInfo(profileInfo);
        userProfile.setUser(user);
        return userProfile;
    }
}
