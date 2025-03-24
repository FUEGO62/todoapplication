package com.bytebuilder.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogInRequest {
    @NotNull
    private String email;

    @NotNull
    private String password;
}
