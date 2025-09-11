package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserServiceImpl;
import com.example.demo.model.CreateUserRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/create")
    public void createUser(@RequestBody CreateUserRequest user) {
        userServiceImpl.createUser(user);
    }
}
