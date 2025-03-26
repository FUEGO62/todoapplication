package com.bytebuilder.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyTaskRequest {
    @NotNull
    private String id;
}
