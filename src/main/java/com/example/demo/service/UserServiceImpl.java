package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CreateUserRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(CreateUserRequest createUserInfo) {
        User user = new User();
        user.setName(createUserInfo.getName());
        userRepository.save(user);
    }

}
