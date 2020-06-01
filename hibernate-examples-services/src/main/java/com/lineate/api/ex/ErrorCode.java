package com.lineate.api.ex;

public enum ErrorCode {
    OK("Ok"),
    INCORRECT_LOGIN_OR_PASSWORD("Incorrect login or password"),
    INVALID_REQUEST("Invalid request"),
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    BAD_REQUEST("Bad request"),
    ACCESS_IS_DENIED("Access is denied"),
    EMAIL_ALREADY_IN_USE("Email already in use");

    private final String errorString;

    ErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
