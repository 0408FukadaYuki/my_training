package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CreateUserRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.IdGenerator;
import com.example.demo.util.SHA256Generator;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(CreateUserRequest createUserInfo) {
        User user = new User();
        user = setCreateUserInfo(createUserInfo);
        userRepository.save(user);
    }

    private User setCreateUserInfo(CreateUserRequest createUserInfo) {
        User user = new User();
        user.setUuid(createUuid());
        user.setUserId(createUserInfo.getUserId());
        user.setName(createUserInfo.getName());
        user.setMail(createUserInfo.getMail());
        user.setProfile(createUserInfo.getProfile());
        user.setBirthDate(createUserInfo.getBirthDate());
        user.setIconImage(createUserInfo.getIconImage());
        user.setPassword(createSHA256Hash(createUserInfo.getPassword()));
        return user;
    }

    private String createUuid() {
        return IdGenerator.generateId().toString();
    }

    private String createSHA256Hash(String password) {
        return SHA256Generator.generateSHA256(password);
    }

}
