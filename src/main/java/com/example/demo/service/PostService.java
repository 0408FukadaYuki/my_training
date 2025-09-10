package com.example.demo.service;

import com.example.demo.model.request.CreatePostRequest;

public interface PostService {
    public void createPost(CreatePostRequest createPostRequest);
    public void deletePost(Long id);
}
