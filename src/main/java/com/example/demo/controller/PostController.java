package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.request.CreatePostRequest;
import com.example.demo.service.PostService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    // 新規投稿作成API
    @PostMapping("/create")
    public void createPost(@RequestBody CreatePostRequest createPostRequest) {
        postService.createPost(createPostRequest);
    }

    
}
