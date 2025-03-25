package com.bytebuilder.services;

import com.bytebuilder.dtos.*;

import java.util.List;


 public interface UserServices {

    UserLogInResponse logIn(UserLogInRequest userLogInRequest);
        
     UserLogInResponse signUp(UserSignUpRequest userSignUpRequest) ;

     UserLogInResponse addTask(AddTaskRequest addTaskRequest) ;
        
     CreateTaskResponse markTaskAsCompleted(FindTaskRequest findTaskRequest) ;


     CreateTaskResponse markTaskAsUncompleted(FindTaskRequest findTaskRequest) ;

     List<CreateTaskResponse> viewPendingTasks(UserLogInRequest userLogInRequest) ;


     List<CreateTaskResponse> viewCompletedTasks(UserLogInRequest userLogInRequest) ;


     List<CreateTaskResponse> deleteTask(FindTaskRequest findTaskRequest) ;
        

}
