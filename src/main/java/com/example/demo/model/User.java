package com.example.demo.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "uuid", length = 36)
    private String uuid;
    @Column(name = "user_id", length = 15)
    private String userId;
    @Column(name = "name", length = 30)
    private String name;
    @Column(name = "mail", length = 50)
    private String mail;
    @Column(name = "profile", length = 100)
    private String profile;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "icon_image")
    private String iconImage;
    @Column(name = "pass_word", length = 64)
    private String password;
}
