package com.bytebuilder.controllers;

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
@CrossOrigin(origins = "http://localhost:5179")
public class UserControllers {

    @Autowired
    private JwtUtil jwtUtil ;

    @Autowired
    private UserServices userServices;


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
    public ResponseEntity<?> addTask(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid CreateTaskRequest createTaskRequest, BindingResult bindingResult) {
        StatusEntity entity = new StatusEntity();
        if (bindingResult.hasErrors()) {
            entity.setMessage(bindingResult.getAllErrors().getFirst().getDefaultMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }

        try{
            AddTaskRequest addTaskRequest = new AddTaskRequest();
            addTaskRequest.setCreateTaskRequest(createTaskRequest);
            addTaskRequest.setEmail(userDetails.getUsername());
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
    public ResponseEntity<?> markAsCompleted(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ModifyTaskRequest modifyTaskRequest, BindingResult bindingResult) {
        StatusEntity entity = new StatusEntity();
        if (bindingResult.hasErrors()) {
            entity.setMessage(bindingResult.getAllErrors().getFirst().getDefaultMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }

        try{
            FindTaskRequest findTaskRequest = new FindTaskRequest();
            findTaskRequest.setId(modifyTaskRequest.getId());
            findTaskRequest.setEmail(userDetails.getUsername());
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
    public ResponseEntity<?> markAsUncompleted(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ModifyTaskRequest modifyTaskRequest, BindingResult bindingResult) {
        StatusEntity entity = new StatusEntity();
        if (bindingResult.hasErrors()) {
            entity.setMessage(bindingResult.getAllErrors().getFirst().getDefaultMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }

        try{
            FindTaskRequest findTaskRequest = new FindTaskRequest();
            findTaskRequest.setId(modifyTaskRequest.getId());
            findTaskRequest.setEmail(userDetails.getUsername());
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
    public ResponseEntity<?> viewCompletedTask(@AuthenticationPrincipal UserDetails userDetails) {
        StatusEntity entity = new StatusEntity();

        try{
            List<CreateTaskResponse> response = userServices.viewCompletedTasks(userDetails.getUsername());
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
    public ResponseEntity<?> viewPendingTask(@AuthenticationPrincipal UserDetails userDetails ) {
        StatusEntity entity = new StatusEntity();

        try{
            List<CreateTaskResponse> response = userServices.viewPendingTasks(userDetails.getUsername());
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
    public ResponseEntity<?> deleteTask(@RequestBody ModifyTaskRequest modifyTaskRequest,@AuthenticationPrincipal UserDetails userDetails, BindingResult bindingResult) {
        StatusEntity entity = new StatusEntity();
        if (bindingResult.hasErrors()) {
            entity.setMessage(bindingResult.getAllErrors().getFirst().getDefaultMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(entity);
        }

        try{
            FindTaskRequest findTaskRequest = new FindTaskRequest();
            findTaskRequest.setId(modifyTaskRequest.getId());
            findTaskRequest.setEmail(userDetails.getUsername());
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
