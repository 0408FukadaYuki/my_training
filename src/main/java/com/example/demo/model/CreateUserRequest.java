package com.example.demo.model;

import java.time.LocalDate;
import lombok.Data;

/**
 * 新規ユーザー作成APIに使用するリクエストモデル
 */
@Data
public class CreateUserRequest {
    private String userId;
    private String name;
    private String mail;
    private String profile;
    private LocalDate birthDate;
    private String iconImage;
    private String password;

    public CreateUserRequest() {
    }
}
