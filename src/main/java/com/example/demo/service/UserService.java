package com.example.demo.service;

import com.example.demo.model.request.CreateUserRequest;

public interface UserService {
    /**
     * 新規ユーザー作成API
     * @param user リクエストボディで受け取る新規ユーザーの情報
     */
    public void createUser(CreateUserRequest user);
}
