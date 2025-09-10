package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.request.CreatePostRequest;
import com.example.demo.service.PostService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/post")
public class PostContorller {
    @Autowired
    private PostService postService;

    // 新規投稿作成API
    @PostMapping("/create")
    public void createPost(@RequestBody CreatePostRequest createPostRequest) {
        postService.createPost(createPostRequest);
    }

    // 投稿削除API
    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable Long id){
        postService.deletePost(id);
    }
    
}
