package com.bytebuilder.dtos;

import com.bytebuilder.data.models.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserLogInResponse {

    private String id;
    private String email;
    private List<Task> tasks;
}
