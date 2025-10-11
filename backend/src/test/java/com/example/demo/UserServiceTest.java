package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.demo.model.request.CreateUserRequest;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @MockitoBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void testCreateUser() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUserId("testUser");
        createUserRequest.setIconImage(null);
        createUserRequest.setName("testUserName");
        createUserRequest.setMail("test@test.com");
        createUserRequest.setProfile("これはUserContollerのテストです");
        createUserRequest.setPassword("1234test5678");
        LocalDate birthDate = LocalDate.of(1997, 04, 8);
        createUserRequest.setBirthDate(birthDate);

        assertDoesNotThrow(() -> userService.createUser(createUserRequest));
    }
}
