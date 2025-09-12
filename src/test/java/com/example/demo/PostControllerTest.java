package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controller.PostController;
import com.example.demo.model.request.CreatePostRequest;
import com.example.demo.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PostController.class)
class PostControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
    ObjectMapper objectMapper;

	@MockitoBean
	PostService postService;

	@Test
	void testCreatePost() throws Exception {
        CreatePostRequest createPostRequest = new CreatePostRequest();
        createPostRequest.setUserId("23e4567-e89b-12d3-a456-426614174000");
        createPostRequest.setContent("Contollerのテストです");
        createPostRequest.setReplyTo(null);

        String request = objectMapper.writeValueAsString(createPostRequest);
		mockMvc.perform(post("/post/create")
				.contentType(MediaType.APPLICATION_JSON)
                .content(request))
				.andExpect(status().isOk());
    }

	@Test
	void testCreateReply() throws Exception {
        CreatePostRequest createPostRequest = new CreatePostRequest();
        createPostRequest.setUserId("23e4567-e89b-12d3-a456-426614174000");
        createPostRequest.setContent("Contollerのテストです");
        createPostRequest.setReplyTo(1);

        String request = objectMapper.writeValueAsString(createPostRequest);
		mockMvc.perform(post("/post/create")
				.contentType(MediaType.APPLICATION_JSON)
                .content(request))
				.andExpect(status().isOk());
    }
}