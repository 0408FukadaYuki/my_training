package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CreateUserRequest;
import com.example.demo.model.UpdateUserRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        Iterable<User> users = userRepository.findAll();
        List<User> response = new ArrayList<>();
        users.forEach(user -> response.add(user));
        return response;
    }

    @Override
    public User getUserById(String id){
        Optional<User> user = userRepository.findById(id);
        User response = user.get();
        return response;
    }

    @Override
    public void CreateUser(CreateUserRequest createUserInfo){
        User user = new User();
        user.setAge(createUserInfo.getAge());
        user.setName(createUserInfo.getName());
        userRepository.save(user);
    }

    @Override
    public void UpdateUserById (UpdateUserRequest updateUserInfo){
        User updateUser = new User();
        updateUser.setAge(updateUserInfo.getAge());
        updateUser.setId(updateUserInfo.getId());
        updateUser.setName(updateUserInfo.getName());
        userRepository.save(updateUser);
    }

    @Override
    public void deleteUserById(String id){
        userRepository.deleteById(id);
    }
}

