package com.bytebuilder.dtos;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTaskRequest {

    @NotNull
    private CreateTaskRequest createTaskRequest;
    @NotNull
    private UserLogInRequest userLogInRequest;
}
