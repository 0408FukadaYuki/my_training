package com.example.demo.model.request;

import lombok.Data;

@Data
public class CreatePostRequest {
    private String userId;
    private String content;
    private Integer replyTo;
}
