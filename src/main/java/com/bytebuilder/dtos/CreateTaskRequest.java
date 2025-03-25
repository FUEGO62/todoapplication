package com.bytebuilder.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskRequest {

    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String description;
}
