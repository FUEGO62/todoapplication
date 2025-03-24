package com.bytebuilder.data.repositories;

import com.bytebuilder.data.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
