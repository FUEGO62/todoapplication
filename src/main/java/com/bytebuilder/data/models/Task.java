package com.bytebuilder.data.models;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Task {

    private String id;
    private String name;
    private String description;
    private boolean isCompleted;
    private boolean isDeleted;
}
