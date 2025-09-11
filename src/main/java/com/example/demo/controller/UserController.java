package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserServiceImpl;
import com.example.demo.model.CreateUserRequest;
import com.example.demo.model.User;
import com.example.demo.model.UserResponse;
import com.example.demo.model.UpdateUserRequest;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/create")
    public void createUser(@RequestBody CreateUserRequest user) {
        userServiceImpl.CreateUser(user);
    }
}
