package com.lineate.api.ex;

public class BadRequestException extends RuntimeException {
    private ErrorCode errorCode;
    private String field;
    private String message;

    public BadRequestException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
        this.message = errorCode.getErrorString();
    }

    public BadRequestException(ErrorCode errorCode, String field) {
        super();
        this.errorCode = errorCode;
        this.field = field;
        this.message = errorCode.getErrorString();
    }

    public BadRequestException(ErrorCode errorCode, String field, String message) {
        super();
        this.errorCode = errorCode;
        this.field = field;
        this.message = message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getField() {
        return field;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
