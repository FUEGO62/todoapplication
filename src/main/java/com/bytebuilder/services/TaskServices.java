package com.bytebuilder.services;


import com.bytebuilder.data.models.User;
import com.bytebuilder.dtos.CreateTaskRequest;
import com.bytebuilder.dtos.CreateTaskResponse;

import java.util.List;


 public interface TaskServices {

     CreateTaskResponse createTask(CreateTaskRequest createTaskRequest) ;
        
     CreateTaskResponse markAsCompleted(String taskId) ;


     CreateTaskResponse markAsUncompleted(String taskId) ;

     List<CreateTaskResponse> getPendingTasks(User user) ;


     List<CreateTaskResponse> getCompletedTasks(User user) ;
 

     CreateTaskResponse deleteTask(String taskId) ;
 
}
