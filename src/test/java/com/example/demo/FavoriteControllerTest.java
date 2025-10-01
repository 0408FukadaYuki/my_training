package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.controller.FavoriteController;
import com.example.demo.model.request.CreateFavoriteRequest;
import com.example.demo.model.response.UserFavoriteResponse;
import com.example.demo.service.FavoriteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(FavoriteController.class)
class FavoriteControllerTest {

        @Autowired
        MockMvc mockMvc;

        @Autowired
        ObjectMapper objectMapper;

        @MockitoBean
        FavoriteService favoriteService;

        private static final LocalDateTime TEST_DATE = LocalDateTime.of(1990, 1, 1, 0, 0, 0);

        @Test
        void testCreateFavorite() throws Exception {
                CreateFavoriteRequest createFavoriteRequest = new CreateFavoriteRequest();
                createFavoriteRequest.setUuid(TestUtil.TEST_UUID1);
                createFavoriteRequest.setPostId((long) 1);

                String request = objectMapper.writeValueAsString(createFavoriteRequest);
                mockMvc.perform(post("/favorite/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(request))
                                .andExpect(status().isOk());
                verify(favoriteService, times(1)).createFavorite(createFavoriteRequest);
        }

        @Test
        void testGetFavorite() throws Exception {
                UserFavoriteResponse response1 = TestUtil.createUserFavoriteResponse(TestUtil.TEST_UUID1, (long) 1,
                                "test_tarou", "テスト 太郎", "これはJunitのテストです", null, TEST_DATE, TEST_DATE);

                UserFavoriteResponse response2 = TestUtil.createUserFavoriteResponse(TestUtil.TEST_UUID2, (long) 2,
                                "test_hanako", "テスト 花子", "これはJunitのテストです", null, TEST_DATE, TEST_DATE);

                List<UserFavoriteResponse> mockResponses = new ArrayList<>(List.of(response1, response2));
                when(favoriteService.getFavorite(TestUtil.TEST_UUID1)).thenReturn(mockResponses);

                mockMvc.perform(get("/favorite/get/{uuid}", TestUtil.TEST_UUID1)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$[0].uuid").value(TestUtil.TEST_UUID1))
                                .andExpect(jsonPath("$[0].postId").value(1))
                                .andExpect(jsonPath("$[0].userId").value("test_tarou"))
                                .andExpect(jsonPath("$[0].userName").value("テスト 太郎"))
                                .andExpect(jsonPath("$[0].content").value("これはJunitのテストです"))
                                .andExpect(jsonPath("$[0].replyTo").isEmpty())
                                .andExpect(jsonPath("$[1].uuid").value(TestUtil.TEST_UUID2))
                                .andExpect(jsonPath("$[1].postId").value(2))
                                .andExpect(jsonPath("$[1].userId").value("test_hanako"))
                                .andExpect(jsonPath("$[1].userName").value("テスト 花子"))
                                .andExpect(jsonPath("$[1].content").value("これはJunitのテストです"))
                                .andExpect(jsonPath("$[1].replyTo").isEmpty());
        }
}
