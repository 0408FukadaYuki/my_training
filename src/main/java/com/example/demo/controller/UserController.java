package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.request.CreateUserRequest;
import com.example.demo.model.request.LoginUserRequest;
import com.example.demo.model.response.LoginUserResponse;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 新規ユーザー作成API
     * 
     * @param user リクエストボディで受け取る新規ユーザーの情報
     */
    @PostMapping("/create")
    public void createUser(@RequestBody CreateUserRequest user) {
        userService.createUser(user);
    }

    /**
     * ユーザログインAPI
     * 
     * @param user リクエストボディで受け取るユーザのログイン情報
     */
    @PostMapping("/login")
    public LoginUserResponse loginUsr(@RequestBody LoginUserRequest user) {
        return userService.loginUser(user);
    }
}
