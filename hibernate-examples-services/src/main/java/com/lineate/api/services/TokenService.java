package com.lineate.api.services;

import com.lineate.api.core.domain.app.User;

public interface TokenService {
    String getToken(User user);
}
