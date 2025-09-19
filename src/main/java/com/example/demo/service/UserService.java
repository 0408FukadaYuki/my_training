package com.example.demo.service;

import com.example.demo.model.request.CreateUserRequest;
import com.example.demo.model.request.LoginUserRequest;
import com.example.demo.model.response.LoginUserResponse;

public interface UserService {
    /**
     * 新規ユーザー作成API
     * @param user リクエストボディで受け取る新規ユーザーの情報
     */
    public void createUser(CreateUserRequest user);

    /**
     * ユーザーログインAPI
     * @param user リクエストボディで受け取るユーザのログイン情報
     */
    public LoginUserResponse loginUser (LoginUserRequest user);
}
