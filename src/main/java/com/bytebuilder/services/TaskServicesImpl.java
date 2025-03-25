package com.bytebuilder.services;

import com.bytebuilder.data.models.Task;
import com.bytebuilder.data.models.User;
import com.bytebuilder.data.repositories.TaskRepository;
import com.bytebuilder.dtos.CreateTaskRequest;
import com.bytebuilder.dtos.CreateTaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.bytebuilder.utils.Mapper.map;

@Service
public class TaskServicesImpl implements TaskServices {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public CreateTaskResponse createTask(CreateTaskRequest createTaskRequest) {

        createTaskRequest.setName(createTaskRequest.getName().trim());

        if (taskRepository.existsByName(createTaskRequest.getName().trim())) {
            Task task = taskRepository.findByName(createTaskRequest.getName().trim());
            if(!task.isDeleted() ) {
                if(!task.isCompleted()) {
                    throw new IllegalArgumentException("Task already exists");
                }
            }
        }

        Task task = map(createTaskRequest);
        task.setCompleted(false);
        task.setDeleted(false);
        return map(taskRepository.save(task));
    }

    @Override
    public CreateTaskResponse markAsCompleted(String taskId) {
        Task task = taskRepository.findById(taskId).get();
        task.setCompleted(true);
        return map(taskRepository.save(task));
    }

    @Override
    public CreateTaskResponse markAsUncompleted(String taskId) {
        Task task = taskRepository.findById(taskId).get();
        task.setCompleted(false);
        return map(taskRepository.save(task));
    }

    @Override
    public List<CreateTaskResponse> getPendingTasks(User user) {
        List<Task> tasks = user.getTasks();
        List<CreateTaskResponse> taskResponses = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.isCompleted() && !task.isDeleted()) {
                taskResponses.add(map(task));
            }
        }
        return taskResponses;
    }

    @Override
    public List<CreateTaskResponse> getCompletedTasks(User user) {
        List<Task> tasks = user.getTasks();
        List<CreateTaskResponse> taskResponses = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isCompleted() && !task.isDeleted()) {
                taskResponses.add(map(task));
            }
        }
        return taskResponses;
    }

    @Override
    public CreateTaskResponse deleteTask(String taskId) {
        Task task = taskRepository.findById(taskId).get();
        task.setDeleted(true);
        return map(taskRepository.save(task));
    }
}
