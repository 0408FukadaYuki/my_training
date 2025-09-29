package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.exception.PostNotCreatedException;
import com.example.demo.exception.UserNotCreatedException;
import com.example.demo.model.User;
import com.example.demo.model.request.CreateUserRequest;
import com.example.demo.model.request.LoginUserRequest;
import com.example.demo.model.response.LoginUserResponse;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.IdGenerator;
import com.example.demo.util.SHA256Generator;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(CreateUserRequest createUserInfo) {
        try {
            User user = new User();
            user = setCreateUserInfo(createUserInfo);
            userRepository.save(user);
        } catch (DataAccessException e) {
            throw new UserNotCreatedException("ユーザーを作成できませんでした。");
        }

    }

    @Override
    public LoginUserResponse loginUser(LoginUserRequest LoginUserRequest) {
        try {
            Optional<User> user = userRepository.findByMail(LoginUserRequest.getMail());
            Boolean sucess = user
                    .map(u -> u.getPassword().equals(createSHA256Hash(LoginUserRequest.getPassword()))).orElse(false);

            LoginUserResponse loginUserResponse = new LoginUserResponse();
            if (sucess) {
                loginUserResponse.setSuccess(true);
                loginUserResponse.setUser(user.get());
            } else {
                loginUserResponse.setSucces(false);
                loginUserResponse.setMessage("ユーザ名またはパスワードが違います");
            }

            return loginUserResponse;
        } catch (DataAccessException e) {
            throw new PostNotCreatedException("予期せぬエラーが発生しました。");
        }

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
