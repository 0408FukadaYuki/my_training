package com.example.demo.model;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新規ユーザー作成APIに使用するリクエストモデル
 */
@NoArgsConstructor
@Data
public class CreateUserRequest {
    private String userId;
    private String name;
    private String mail;
    private String profile;
    private LocalDate birthDate;
    private String iconImage;
    private String password;
}
