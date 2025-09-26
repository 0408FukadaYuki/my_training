package com.example.demo.model.response;

import com.example.demo.model.User;

import lombok.Data;

/**
 * ユーザーログインAPIで使用するレスポンスモデル
 */
@Data
public class LoginUserResponse {
    Boolean succes;
    String message;
    User user;
}
