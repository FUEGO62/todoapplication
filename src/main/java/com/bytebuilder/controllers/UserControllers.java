package com.bytebuilder.controllers;

import com.bytebuilder.data.models.Status;
import com.bytebuilder.dtos.UserSignUpRequest;
import com.bytebuilder.dtos.UserLogInResponse;
import com.bytebuilder.services.UserServices;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserControllers {

    @Autowired
    private UserServices userServices;

    @PostMapping("signUp")
    public ResponseEntity<?> signUp(@RequestBody @Valid UserSignUpRequest userSignUpRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            entity.setMessage(bindingResult.getAllErrors().getFirst().getDefaultMessage());
            entity.setStatus(Status.ERROR);
            return entity;
        }

        try{
            UserLogInResponse response = userServices.signUp(userSignUpRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);


            entity.setMessage(HttpStatus.CREATED.toString());
        }
        catch(Exception e){
            entity.setMessage(e.getMessage());
            entity.setStatus(Status.ERROR);
        }
        return entity;
    }
}
