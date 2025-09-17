package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.controller.PostController;
import com.example.demo.model.request.CreatePostRequest;
import com.example.demo.model.response.GetAllPostResponse;
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

    @Test
    void testDeletePost() throws Exception {
        mockMvc.perform(delete("/post/delete/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllPost() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(1990, 1, 1, 0, 0, 0);
        GetAllPostResponse response1 = new GetAllPostResponse();
        response1.setUuid("a23e4567-e89b-12d3-a456-426614174000");
        response1.setPostId((long) 1);
        response1.setUserId("test_tarou");
        response1.setUserName("テスト 太郎");
        response1.setContent("これはJunitのテストです");
        response1.setReplyTo(null);
        response1.setCreatedAt(localDateTime);

        GetAllPostResponse response2 = new GetAllPostResponse();
        response2.setUuid("b23e4567-e89b-12d3-a456-426614174000");
        response2.setPostId((long) 2);
        response2.setUserId("test_hanako");
        response2.setUserName("テスト 花子");
        response2.setContent("これはJunitのテストです");
        response2.setReplyTo(null);
        response2.setCreatedAt(localDateTime);

        List<GetAllPostResponse> mockResponses = new ArrayList<>(List.of(response1, response2));
        when(postService.findAllPost()).thenReturn(mockResponses);

        mockMvc.perform(get("/post/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid").value("a23e4567-e89b-12d3-a456-426614174000"))
                .andExpect(jsonPath("$[0].postId").value(1))
                .andExpect(jsonPath("$[0].userId").value("test_tarou"))
                .andExpect(jsonPath("$[0].userName").value("テスト 太郎"))
                .andExpect(jsonPath("$[0].content").value("これはJunitのテストです"))
                .andExpect(jsonPath("$[0].replyTo").isEmpty())
                .andExpect(jsonPath("$[1].uuid").value("b23e4567-e89b-12d3-a456-426614174000"))
                .andExpect(jsonPath("$[1].postId").value(2))
                .andExpect(jsonPath("$[1].userId").value("test_hanako"))
                .andExpect(jsonPath("$[1].userName").value("テスト 花子"))
                .andExpect(jsonPath("$[1].content").value("これはJunitのテストです"))
                .andExpect(jsonPath("$[1].replyTo").isEmpty());
    }
}