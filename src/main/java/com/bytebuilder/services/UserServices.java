package com.bytebuilder.services;

import com.bytebuilder.data.models.User;
import com.bytebuilder.data.repositories.UserRepository;
import com.bytebuilder.dtos.UserLogInRequest;
import com.bytebuilder.dtos.UserSignUpRequest;
import com.bytebuilder.dtos.UserLogInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.bytebuilder.utils.Mapper.map;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public UserLogInResponse logIn(UserLogInRequest userLogInRequest) {
        User user = userRepository.findByEmail(userLogInRequest.getEmail());
        if(!userLogInRequest.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("invalid username or password");
        }
        return map(user);
    }

    public UserLogInResponse signUp(UserSignUpRequest userSignUpRequest) {
        validateUser(userSignUpRequest);
        User user = map(userSignUpRequest);
        user.setTasks(new ArrayList<>());
        return map(userRepository.save(user));
    }

    private void validateUser(UserSignUpRequest userSignUpRequest) {

        preventDuplicates(userSignUpRequest);
    }

    private void preventDuplicates(UserSignUpRequest userSignUpRequest) {
        if(userRepository.existsByEmail(userSignUpRequest.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
    }


}
