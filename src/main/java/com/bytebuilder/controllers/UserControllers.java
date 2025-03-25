package com.bytebuilder.controllers;

import com.bytebuilder.data.models.User;
import com.bytebuilder.dtos.*;
import com.bytebuilder.services.UserServices;
import com.bytebuilder.utils.JwtUtil;
import com.bytebuilder.utils.StatusEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControllers {

    @Autowired
    private JwtUtil jwtUtil ;

    @Autowired
    private UserServices userServices;


    @PostMapping("/getEmail")
    public ResponseEntity<String> getUsername(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
            return ResponseEntity.ok("Username from JWT: " + userDetails.getUsername());
        } else {
            return ResponseEntity.ok("No authenticated user found.");
        }
    }

    @PostMapping ("/logIn")
    public ResponseEntity<?> logIn(@RequestBody @Valid UserLogInRequest userLogInRequest, BindingResult bindingResult) {
        StatusEntity entity = new StatusEntity();
        if (bindingResult.hasErrors()) {
            entity.setMessage(bindingResult.getAllErrors().getFirst().getDefaultMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }

        try{
            userServices.logIn(userLogInRequest);
            String token = jwtUtil.generateToken(userLogInRequest.getEmail());
            entity.setResponse(token);
            entity.setMessage(HttpStatus.OK.toString());
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        }
        catch (NullPointerException e){
            entity.setMessage("invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }
        catch(Exception e){
            entity.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody @Valid UserSignUpRequest userSignUpRequest, BindingResult bindingResult) {

        StatusEntity entity = new StatusEntity();
        if (bindingResult.hasErrors()) {
            entity.setMessage(bindingResult.getAllErrors().getFirst().getDefaultMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }

        try{
            UserLogInResponse response = userServices.signUp(userSignUpRequest);
            entity.setResponse(response);
            entity.setMessage(HttpStatus.CREATED.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(entity);
        }
        catch(Exception e){
            entity.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }
    }

    @PostMapping("/addTask")
    public ResponseEntity<?> addTask(@RequestBody @Valid AddTaskRequest addTaskRequest, BindingResult bindingResult) {
        StatusEntity entity = new StatusEntity();
        if (bindingResult.hasErrors()) {
            entity.setMessage(bindingResult.getAllErrors().getFirst().getDefaultMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }

        try{
            UserLogInResponse response = userServices.addTask(addTaskRequest);
            entity.setResponse(response);
            entity.setMessage(HttpStatus.CREATED.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(entity);
        }
        catch (NullPointerException e){
            entity.setMessage("invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }
        catch(Exception e){
            entity.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }
    }

    @PostMapping("/markAsCompleted")
    public ResponseEntity<?> markAsCompleted(@RequestBody @Valid FindTaskRequest findTaskRequest, BindingResult bindingResult) {
        StatusEntity entity = new StatusEntity();
        if (bindingResult.hasErrors()) {
            entity.setMessage(bindingResult.getAllErrors().getFirst().getDefaultMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }

        try{
            CreateTaskResponse response = userServices.markTaskAsCompleted(findTaskRequest);
            entity.setResponse(response);
            entity.setMessage(HttpStatus.OK.toString());
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        }
        catch (NullPointerException e){
            entity.setMessage("invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }
        catch(Exception e){
            entity.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }
    }

    @PostMapping("/markAsUncompleted")
    public ResponseEntity<?> markAsUncompleted(@RequestBody @Valid FindTaskRequest findTaskRequest, BindingResult bindingResult) {
        StatusEntity entity = new StatusEntity();
        if (bindingResult.hasErrors()) {
            entity.setMessage(bindingResult.getAllErrors().getFirst().getDefaultMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }

        try{
            CreateTaskResponse response = userServices.markTaskAsUncompleted(findTaskRequest);
            entity.setResponse(response);
            entity.setMessage(HttpStatus.OK.toString());
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        }
        catch (NullPointerException e){
            entity.setMessage("invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }
        catch(Exception e){
            entity.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }
    }

    @PostMapping("/viewCompletedTasks")
    public ResponseEntity<?> viewCompletedTask(@RequestBody @Valid UserLogInRequest userLogInRequest, BindingResult bindingResult) {
        StatusEntity entity = new StatusEntity();
        if (bindingResult.hasErrors()) {
            entity.setMessage(bindingResult.getAllErrors().getFirst().getDefaultMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }

        try{
            List<CreateTaskResponse> response = userServices.viewCompletedTasks(userLogInRequest);
            entity.setResponse(response);
            entity.setMessage(HttpStatus.OK.toString());
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        }
        catch (NullPointerException e){
            entity.setMessage("invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }
        catch(Exception e){
            entity.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }
    }

    @PostMapping("/viewPendingTasks")
    public ResponseEntity<?> viewPendingTask(@RequestBody @Valid UserLogInRequest userLogInRequest, BindingResult bindingResult) {
        StatusEntity entity = new StatusEntity();
        if (bindingResult.hasErrors()) {
            entity.setMessage(bindingResult.getAllErrors().getFirst().getDefaultMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }

        try{
            List<CreateTaskResponse> response = userServices.viewPendingTasks(userLogInRequest);
            entity.setResponse(response);
            entity.setMessage(HttpStatus.OK.toString());
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        }
        catch (NullPointerException e){
            entity.setMessage("invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }
        catch(Exception e){
            entity.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }
    }

    @PostMapping("/deleteTask")
    public ResponseEntity<?> deleteTask(@RequestBody @Valid FindTaskRequest findTaskRequest, BindingResult bindingResult) {
        StatusEntity entity = new StatusEntity();
        if (bindingResult.hasErrors()) {
            entity.setMessage(bindingResult.getAllErrors().getFirst().getDefaultMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }

        try{
            List<CreateTaskResponse> response = userServices.deleteTask(findTaskRequest);
            entity.setResponse(response);
            entity.setMessage(HttpStatus.OK.toString());
            return ResponseEntity.status(HttpStatus.OK).body(entity);
        }
        catch (NullPointerException e){
            entity.setMessage("invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }
        catch(Exception e){
            entity.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }
    }

}
