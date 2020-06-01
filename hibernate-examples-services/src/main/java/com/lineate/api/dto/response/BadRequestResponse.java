package com.lineate.api.dto.response;

import com.lineate.api.ex.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BadRequestResponse {
    private String field;
    private String errorMessage;
    private ErrorCode errorCode;
}
