package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.demo.exception.FavoriteNotCreatedException;
import com.example.demo.exception.FavoriteNotGetException;
import com.example.demo.model.Favorite;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.model.request.CreateFavoriteRequest;
import com.example.demo.model.response.UserFavoriteResponse;
import com.example.demo.repository.FavoriteRepository;
import com.example.demo.service.FavoriteService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FavoriteServiceTest {
    @MockitoBean
    private FavoriteRepository favoriteRepository;

    @Autowired
    private FavoriteService favoriteService;

    @Test
    void testCreateFavorite() {
        CreateFavoriteRequest createFavoriteRequest = new CreateFavoriteRequest();
        createFavoriteRequest.setUuid(TestUtil.TEST_UUID2);
        createFavoriteRequest.setPostId((long) 1);

        assertDoesNotThrow(() -> favoriteService.createFavorite(createFavoriteRequest));
    }

    @Test
    void testCreateFavoriteThrowsException() {
        CreateFavoriteRequest createFavoriteRequest = new CreateFavoriteRequest();
        createFavoriteRequest.setUuid(TestUtil.TEST_UUID2);
        createFavoriteRequest.setPostId((long) 1);
        DataAccessException dataAccessException = new DataAccessException("error") {
        };
        when(favoriteRepository.save(any())).thenThrow(dataAccessException);
        FavoriteNotCreatedException exception = assertThrows(FavoriteNotCreatedException.class,
                () -> favoriteService.createFavorite(createFavoriteRequest));
        assertEquals(exception.getMessage(), "お気に入りを保存できませんでした。");
    }

    @Test
    void testGetFavorite() {
        LocalDateTime localDateTime = LocalDateTime.of(1990, 1, 1, 0, 0, 0);
        UserFavoriteResponse response1 = TestUtil.createUserFavoriteResponse(TestUtil.TEST_UUID1, (long) 1,
                "test_tarou", "テスト 太郎", "これはJunitのテストです", null, localDateTime, localDateTime);
        UserFavoriteResponse response2 = TestUtil.createUserFavoriteResponse(TestUtil.TEST_UUID2, (long) 2,
                "test_hanako", "テスト 花子", "これはJunitのテストです", null, localDateTime, localDateTime);
        List<UserFavoriteResponse> expectedResponses = new ArrayList<>(List.of(response1, response2));

        User user1 = TestUtil.createUser1();
        Post post1 = TestUtil.createPost((long) 1, user1, "これはJunitのテストです", null, localDateTime);
        Favorite favorite1 = TestUtil.createFavoite(user1, post1, localDateTime);
        User user2 = TestUtil.createUser2();
        Post post2 = TestUtil.createPost((long) 2, user2, "これはJunitのテストです", null, localDateTime);
        Favorite favorite2 = TestUtil.createFavoite(user2, post2, localDateTime);
        List<Favorite> mockResponse = new ArrayList<>(List.of(favorite1, favorite2));

        User findUser = new User();
        findUser.setUuid(TestUtil.TEST_UUID1);
        when(favoriteRepository.findByUserOrderByCreatedAtDesc(findUser)).thenReturn(mockResponse);

        List<UserFavoriteResponse> acutualResponse = favoriteService
                .getFavorite(TestUtil.TEST_UUID1);
        assertEquals(expectedResponses, acutualResponse);
    }

    @Test
    void testGetFavoriteThrowsException() {
        DataAccessException dataAccessException = new DataAccessException("error") {
        };
        User findUser = new User();
        findUser.setUuid(TestUtil.TEST_UUID1);
        when(favoriteRepository.findByUserOrderByCreatedAtDesc(findUser)).thenThrow(dataAccessException);
        FavoriteNotGetException exception = assertThrows(FavoriteNotGetException.class,
                () -> favoriteService.getFavorite(TestUtil.TEST_UUID1));
        assertEquals(exception.getMessage(), "お気に入りを取得できませんでした。");
    }
}
