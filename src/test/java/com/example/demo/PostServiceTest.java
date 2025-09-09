package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import com.example.demo.model.request.CreatePostRequest;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @MockitoBean
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Test
    void testCreatePost() {
        CreatePostRequest createPostRequest = new CreatePostRequest();
        createPostRequest.setUserId("23e4567-e89b-12d3-a456-426614174000");
        createPostRequest.setContent("Contollerのテストです");
        createPostRequest.setReplyTo(null);

        assertDoesNotThrow(() -> postService.createPost(createPostRequest));
    }
}
