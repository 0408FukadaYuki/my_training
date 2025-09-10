package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.exception.PostNotCreatedException;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.model.request.CreatePostRequest;
import com.example.demo.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public void createPost(CreatePostRequest createPostRequest) {
        try {
            Post createPostInfo = new Post();
            User user = new User();
            user.setUuid(createPostRequest.getUserId());
            createPostInfo.setUserId(user);
            createPostInfo.setReplyTo(createPostRequest.getReplyTo());
            createPostInfo.setContent(createPostRequest.getContent());
            postRepository.save(createPostInfo);
        } catch (DataAccessException e) {
            throw new PostNotCreatedException("投稿を作成できませんでした。");
        }
    }
}
