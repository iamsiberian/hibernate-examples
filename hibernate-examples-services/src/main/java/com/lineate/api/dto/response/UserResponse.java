package com.lineate.api.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private String email;
    private String name;
}
