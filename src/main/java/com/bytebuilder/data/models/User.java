package com.bytebuilder.data.models;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document
@Getter
@Setter
public class User {

    private String id;
    private String email;
    private String password;
    private List<Task> tasks;
}
