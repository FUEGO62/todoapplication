package com.bytebuilder.services;

import com.bytebuilder.data.models.Task;
import com.bytebuilder.data.models.User;
import com.bytebuilder.data.repositories.UserRepository;
import com.bytebuilder.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.bytebuilder.utils.Mapper.map;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskServices taskServices;

    @Override
    public UserLogInResponse logIn(UserLogInRequest userLogInRequest) {
        User user = userRepository.findByEmail(userLogInRequest.getEmail().trim());
        if(!userLogInRequest.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("invalid username or password");
        }
        return map(user);
    }

    @Override
    public UserLogInResponse signUp(UserSignUpRequest userSignUpRequest) {
        validateUser(userSignUpRequest);
        User user = map(userSignUpRequest);
        user.setTasks(new ArrayList<>());
        return map(userRepository.save(user));
    }

    private void validatePassword(UserSignUpRequest userSignUpRequest) {
        String password = userSignUpRequest.getPassword();
        if(password.contains(" ")){
            throw new IllegalArgumentException("password cannot contain spaces");
        }
    }

    private void validateUser(UserSignUpRequest userSignUpRequest) {

        validatePassword(userSignUpRequest);
        preventDuplicates(userSignUpRequest);
    }

    private void preventDuplicates(UserSignUpRequest userSignUpRequest) {
        if(userRepository.existsByEmail(userSignUpRequest.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
    }

    @Override
    public UserLogInResponse addTask(AddTaskRequest addTaskRequest) {

        CreateTaskRequest createTaskRequest = addTaskRequest.getCreateTaskRequest();
        User user = userRepository.findByEmail(addTaskRequest.getEmail());
        Task task = map(taskServices.createTask(createTaskRequest));
        List<Task> tasks = user.getTasks();
        tasks.add(task);
        user.setTasks(tasks);
        userRepository.save(user);
        return map(user);
    }

    @Override
    public CreateTaskResponse markTaskAsCompleted(FindTaskRequest findTaskRequest) {
        String taskId = findTaskRequest.getId();
        String email = findTaskRequest.getEmail();
        CreateTaskResponse response = taskServices.markAsCompleted(taskId);
        User user = userRepository.findByEmail(email);
        List<Task> tasks = user.getTasks();
        for (Task task : tasks) {
            if(task.getId().equals(taskId)) {
                task.setCompleted(true);
            }
        }
        user.setTasks(tasks);
        userRepository.save(user);
        return response;
    }

    @Override
    public CreateTaskResponse markTaskAsUncompleted(FindTaskRequest findTaskRequest) {
        String taskId = findTaskRequest.getId();
        String email = findTaskRequest.getEmail();
        CreateTaskResponse response = taskServices.markAsUncompleted(taskId);
        User user = userRepository.findByEmail(email);
        List<Task> tasks = user.getTasks();
        for (Task task : tasks) {
            if(task.getId().equals(taskId)) {
                task.setCompleted(true);
            }
        }
        user.setTasks(tasks);
        userRepository.save(user);
        return response;
    }

    @Override
    public List<CreateTaskResponse> viewPendingTasks(String email) {

        User user = userRepository.findByEmail(email);
        return taskServices.getPendingTasks(user);
    }

    @Override
    public List<CreateTaskResponse> viewCompletedTasks(String email) {

        User user = userRepository.findByEmail(email);
        return taskServices.getCompletedTasks(user);
    }

    @Override
    public List<CreateTaskResponse> deleteTask(FindTaskRequest findTaskRequest) {

        User user = userRepository.findByEmail(findTaskRequest.getEmail());
        Task task = map(taskServices.deleteTask(findTaskRequest.getId()));
        List<Task> tasks = user.getTasks();
        for(Task task1 : tasks) {
            if(task1.getId().equals(task.getId())) {
                task1.setDeleted(true);
            }
        }
        user.setTasks(tasks);
        userRepository.save(user);
        return taskServices.getPendingTasks(user);

    }
}
