package com.bytebuilder.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogInRequest {
    @NotNull
    @NotBlank(message = "email is a required field")
    private String email;

    @NotNull
    @NotBlank(message = "password is a required field")
    private String password;
}
