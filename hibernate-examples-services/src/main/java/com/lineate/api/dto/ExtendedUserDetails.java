package com.lineate.api.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ExtendedUserDetails extends org.springframework.security.core.userdetails.User {
    private final Integer userId;

    public ExtendedUserDetails(
        Integer userId, String username, String password,
        boolean enabled,
        boolean accountNonExpired,
        boolean credentialsNonExpired,
        boolean accountNonLocked,
        Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }
}