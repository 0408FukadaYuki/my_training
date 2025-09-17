package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.exception.PostNotCreatedException;
import com.example.demo.exception.PostNotGetException;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.model.request.CreatePostRequest;
import com.example.demo.model.response.GetAllPostResponse;
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

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<GetAllPostResponse> findAllPost() {
        try {
            Iterable<Post> posts = postRepository.findAll();
            List<GetAllPostResponse> response = new ArrayList<>();
            posts.forEach(post -> {
                GetAllPostResponse res = new GetAllPostResponse();
                res.setUuid(post.getUserId().getUuid());
                res.setPostId(post.getId());
                res.setUserId(post.getUserId().getUserId());
                res.setUserName(post.getUserId().getName());
                res.setContent(post.getContent());
                res.setReplyTo(post.getReplyTo());
                res.setCreatedAt(post.getCreatedAt());
                response.add(res);
            });
            return response;
        } catch (Exception e) {
            throw new PostNotGetException("投稿を取得できませんでした。");
        }

    }
}
