package com.bytebuilder.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskResponse {

    private String id;
    private String name;
    private String description;
    private boolean isCompleted;
    private boolean isDeleted;
}
