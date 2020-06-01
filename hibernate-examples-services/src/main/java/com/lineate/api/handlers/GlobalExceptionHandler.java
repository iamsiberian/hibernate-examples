package com.lineate.api.handlers;

import com.lineate.api.ex.BadRequestException;
import com.lineate.api.dto.response.BadRequestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestResponse> handleBadRequestExceptionException(final BadRequestException ex) {
        LOGGER.error("Handle BadRequestExceptionException", ex);
        return ResponseEntity.badRequest().body(new BadRequestResponse(
            ex.getField(),
            ex.getMessage(),
            ex.getErrorCode()
        ));
    }
}
