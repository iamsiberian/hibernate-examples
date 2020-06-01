package com.lineate.api.services;

import com.lineate.api.core.domain.onetoone.mapsid.User;

public interface TokenService {
    String getToken(User user);
}
