package com.example.demo.model;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private Long id;
    private int age;
    private String name;

    UpdateUserRequest(){}
}
