package com.bytebuilder.utils;

import com.bytebuilder.data.models.User;
import com.bytebuilder.dtos.UserSignUpRequest;
import com.bytebuilder.dtos.UserLogInResponse;

public class Mapper {

    public static UserLogInResponse map(User user) {
        UserLogInResponse userLogInResponse = new UserLogInResponse();
        userLogInResponse.setId(user.getId());
        userLogInResponse.setEmail(user.getEmail());
        userLogInResponse.setTasks(user.getTasks());

        return userLogInResponse;
    }

    public static User map(UserSignUpRequest userSignUpRequest) {
        User user = new User();
        user.setEmail(userSignUpRequest.getEmail());
        user.setPassword(userSignUpRequest.getPassword());

        return user;
    }
}
