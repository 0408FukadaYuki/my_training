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
import com.example.demo.model.FavoritePK;
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
        createFavoriteRequest.setUuid("23e4567-e89b-12d3-a456-426614174000");
        createFavoriteRequest.setPostId((long) 1);

        assertDoesNotThrow(() -> favoriteService.createFavorite(createFavoriteRequest));
    }

    @Test
    void testCreateFavoriteThrowException() {
        CreateFavoriteRequest createFavoriteRequest = new CreateFavoriteRequest();
        createFavoriteRequest.setUuid("23e4567-e89b-12d3-a456-426614174000");
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
        UserFavoriteResponse response1 = new UserFavoriteResponse();
        response1.setUuid("a23e4567-e89b-12d3-a456-426614174000");
        response1.setPostId((long) 1);
        response1.setUserId("test_tarou");
        response1.setUserName("テスト 太郎");
        response1.setContent("これはJunitのテストです");
        response1.setReplyTo(null);
        response1.setFavoriteCreatedAt(localDateTime);
        response1.setPostCreatedAt(localDateTime);

        UserFavoriteResponse response2 = new UserFavoriteResponse();
        response2.setUuid("b23e4567-e89b-12d3-a456-426614174000");
        response2.setPostId((long) 2);
        response2.setUserId("test_hanako");
        response2.setUserName("テスト 花子");
        response2.setContent("これはJunitのテストです");
        response2.setReplyTo(null);
        response2.setFavoriteCreatedAt(localDateTime);
        response2.setPostCreatedAt(localDateTime);

        List<UserFavoriteResponse> expectedResponses = new ArrayList<>(List.of(response1, response2));

        Favorite favorite1 = new Favorite();
        Post post1 = new Post();
        post1.setId((long) 1);
        post1.setContent("これはJunitのテストです");
        post1.setReplyTo(null);
        post1.setCreatedAt(localDateTime);
        User user1 = new User();
        user1.setUuid("a23e4567-e89b-12d3-a456-426614174000");
        user1.setUserId("test_tarou");
        user1.setName("テスト 太郎");
        post1.setUserId(user1);
        FavoritePK favoritePK1 = new FavoritePK();
        favoritePK1.setPostId((long) 1);
        favoritePK1.setUserId("a23e4567-e89b-12d3-a456-426614174000");

        favorite1.setFavoritePK(favoritePK1);
        favorite1.setPost(post1);
        favorite1.setUser(user1);
        favorite1.setCreatedAt(localDateTime);

        Favorite favorite2 = new Favorite();
        Post post2 = new Post();
        post2.setContent("これはJunitのテストです");
        post2.setReplyTo(null);
        post2.setCreatedAt(localDateTime);
        post2.setId((long) 2);
        User user2 = new User();
        user2.setUuid("b23e4567-e89b-12d3-a456-426614174000");
        user2.setUserId("test_hanako");
        user2.setName("テスト 花子");
        post2.setUserId(user2);
        FavoritePK favoritePK2 = new FavoritePK();
        favoritePK2.setPostId((long) 2);
        favoritePK2.setUserId("a23e4567-e89b-12d3-a456-426614174000");

        favorite2.setFavoritePK(favoritePK2);
        favorite2.setPost(post2);
        favorite2.setUser(user1);
        favorite2.setCreatedAt(localDateTime);

        Iterable<Favorite> mockResponse = new ArrayList<>(List.of(favorite1, favorite2));

        User findUser = new User();
        findUser.setUuid("a23e4567-e89b-12d3-a456-426614174000");
        when(favoriteRepository.findByUser(findUser)).thenReturn(mockResponse);

        List<UserFavoriteResponse> acutualResponse = favoriteService
                .getFavorite("a23e4567-e89b-12d3-a456-426614174000");

        assertEquals(expectedResponses, acutualResponse);
    }

    @Test
    void testGetFavoriteThrowsException() {
        DataAccessException dataAccessException = new DataAccessException("error") {
        };
        User findUser = new User();
        findUser.setUuid("a23e4567-e89b-12d3-a456-426614174000");
        when(favoriteRepository.findByUser(findUser)).thenThrow(dataAccessException);
        FavoriteNotGetException exception = assertThrows(FavoriteNotGetException.class,
                () -> favoriteService.getFavorite("a23e4567-e89b-12d3-a456-426614174000"));
        assertEquals(exception.getMessage(), "お気に入りを取得できませんでした。");
    }
}
