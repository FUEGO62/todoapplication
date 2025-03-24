package com.bytebuilder.data.repositories;

import com.bytebuilder.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
}
