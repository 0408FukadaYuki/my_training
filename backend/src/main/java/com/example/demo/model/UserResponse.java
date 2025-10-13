package com.example.demo.model;
import lombok.Data;
@Data
public class UserResponse {
    private Long id;
    private String name;
    private int age;
    public UserResponse(){}   
}
