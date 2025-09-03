package com.example.demo.service;
import java.util.List;

import com.example.demo.model.CreateUserRequest;
import com.example.demo.model.User;
import com.example.demo.model.UpdateUserRequest;

public interface UserService {
    public List<User> getUsers();
    public User getUserById(Long id);
    public void CreateUser(CreateUserRequest user);
    public void UpdateUserById(UpdateUserRequest user);
    public void deleteUserById(Long id);
}
