package com.bytebuilder.services;

import com.bytebuilder.data.repositories.UserRepository;
import com.bytebuilder.dtos.UserSignUpRequest;
import com.bytebuilder.dtos.UserLogInResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServicesTest {

    @Autowired
    private UserServices userServices;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUserCanSignUp() {
        UserSignUpRequest userSignUpRequest = new UserSignUpRequest();
        userSignUpRequest.setEmail("jones@gmail.com");
        userSignUpRequest.setPassword("password");
        UserLogInResponse response = userServices.signUp(userSignUpRequest);
        assertEquals(userSignUpRequest.getEmail(), response.getEmail());
        assertThrows(IllegalArgumentException.class, () -> userServices.signUp(userSignUpRequest));
        assertEquals(userRepository.count(), 1);
    }
}