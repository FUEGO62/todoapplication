package com.bytebuilder.utils;

import com.bytebuilder.data.models.Task;
import com.bytebuilder.data.models.User;
import com.bytebuilder.dtos.CreateTaskRequest;
import com.bytebuilder.dtos.CreateTaskResponse;
import com.bytebuilder.dtos.UserSignUpRequest;
import com.bytebuilder.dtos.UserLogInResponse;

public class Mapper {

    public static UserLogInResponse map(User user) {
        UserLogInResponse userLogInResponse = new UserLogInResponse();
        userLogInResponse.setId(user.getId());
        userLogInResponse.setEmail(user.getEmail().trim());
        userLogInResponse.setTasks(user.getTasks());

        return userLogInResponse;
    }

    public static User map(UserSignUpRequest userSignUpRequest) {
        User user = new User();
        user.setEmail(userSignUpRequest.getEmail().trim());
        user.setPassword(userSignUpRequest.getPassword().trim());

        return user;
    }

    public static Task map(CreateTaskRequest createTaskRequest) {
        Task task = new Task();
        task.setDescription(createTaskRequest.getDescription().trim());
        task.setName(createTaskRequest.getName().trim());
        return task;
    }

    public static CreateTaskResponse map(Task task) {
        CreateTaskResponse createTaskResponse = new CreateTaskResponse();
        createTaskResponse.setDescription(task.getDescription().trim());
        createTaskResponse.setName(task.getName().trim());
        createTaskResponse.setId(task.getId());
        createTaskResponse.setCompleted(task.isCompleted());
        createTaskResponse.setDeleted(task.isDeleted());
        return createTaskResponse;
    }

    public static Task map(CreateTaskResponse createTaskResponse) {
        Task task = new Task();
        task.setDescription(createTaskResponse.getDescription().trim());
        task.setName(createTaskResponse.getName().trim());
        task.setId(createTaskResponse.getId());
        task.setCompleted(createTaskResponse.isCompleted());
        task.setDeleted(createTaskResponse.isDeleted());
        return task;
    }

    public static User map(UserLogInResponse userLogInResponse) {
        User user = new User();
        user.setEmail(userLogInResponse.getEmail().trim());
        user.setTasks(userLogInResponse.getTasks());
        user.setId(userLogInResponse.getId());
        return user;
    }
}
