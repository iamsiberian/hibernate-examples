package com.lineate.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {
    @Pattern(regexp = "[A-z0-9_]+@[A-z]+\\.[a-z]{2,6}", message = "Wrong email")
    @NotNull(message = "Email cannot be null")
    private String email;

    @Size(min = 6, message = "Password length should be greater then 6")
    @NotNull(message = "Password cannot be null")
    private String password;

    @Size(max = 512)
    private String name;
}
