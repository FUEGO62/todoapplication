package com.bytebuilder.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindTaskRequest {

    @NotNull
    private UserLogInRequest userLogInRequest;
    @NotNull
    private String id;
}
