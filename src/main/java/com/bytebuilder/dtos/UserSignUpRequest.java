package com.bytebuilder.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserSignUpRequest {
    @Email(message = "invalid email")
    @NotNull(message = "email is a required field")
    private String email;

    @Size(min = 8, message = "password is not secure enough")
    @NotNull(message = "email is a required field")
    private String password;
}
