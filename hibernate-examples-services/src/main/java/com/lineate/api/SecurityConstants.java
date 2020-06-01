package com.lineate.api;

public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenerateJwtToken";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SET_AUTH_HEADER_STRING = "Set-Authorization";
}
