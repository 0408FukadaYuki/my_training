package com.example.demo.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザーログインAPIで使用するリクエストモデル
 */
@Data
@NoArgsConstructor
public class LoginUserRequest {
    private String mail;
    private String password;
}
