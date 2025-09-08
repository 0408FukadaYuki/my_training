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

    @RequestMapping("/all")
    public List<UserResponse> getUser() {
        List<User> users = userServiceImpl.getUsers();
        List<UserResponse> response = new ArrayList<>();
        users.forEach(user -> {
            UserResponse userResponse = new UserResponse();
            // userResponse.setId(user.getId());
            // userResponse.setAge(user.getAge());
            userResponse.setName(user.getName());
            response.add(userResponse);
        });
        return response;
    }

    @RequestMapping("/{id}")
    public UserResponse getUserById(@PathVariable("id") Long id) {
        User user = userServiceImpl.getUserById(id);
        UserResponse response = new UserResponse();
        // response.setId(user.getId());
        // response.setAge(user.getAge());
        response.setName(user.getName());
        return response;
    }

    @PostMapping("/create")
    public void createUser(@RequestBody CreateUserRequest user) {
        userServiceImpl.CreateUser(user);
    }

    @PutMapping("/update")
    public void updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        userServiceImpl.UpdateUserById(updateUserRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userServiceImpl.deleteUserById(id);
    }
    
}
