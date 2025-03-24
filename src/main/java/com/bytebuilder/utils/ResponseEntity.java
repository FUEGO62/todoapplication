package com.bytebuilder.utils;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseEntity {

    private Object response;
    private String message;
    private Status status;
}
