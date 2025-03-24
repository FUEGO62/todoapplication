package com.bytebuilder.utils;


import com.bytebuilder.data.models.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseEntity {

    private Object response;
    private String message;
    private Status status;
}
