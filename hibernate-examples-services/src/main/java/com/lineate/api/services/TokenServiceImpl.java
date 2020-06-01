package com.lineate.api.services;

import com.lineate.api.core.domain.onetoone.mapsid.User;
import com.lineate.api.ex.BadRequestException;
import com.lineate.api.core.repositories.onetoone.mapsid.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

import static com.lineate.api.SecurityConstants.EXPIRATION_TIME;
import static com.lineate.api.SecurityConstants.SECRET;

@Service
public class TokenServiceImpl implements TokenService {
    private UserRepository userRepository;

    public TokenServiceImpl() {
    }

    @Autowired
    public TokenServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String getToken(User user) throws BadRequestException {

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setId(String.valueOf(user.getId()))
                .claim("clientType", user.getUserType())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(SECRET.getBytes()))
                .compact();
    }
}
