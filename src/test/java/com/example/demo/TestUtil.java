package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.model.Favorite;
import com.example.demo.model.FavoritePK;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.model.response.UserFavoriteResponse;

public class TestUtil {
    public static final String TEST_UUID1 = "a23e4567-e89b-12d3-a456-426614174000";
    public static final String TEST_UUID2 = "b23e4567-e89b-12d3-a456-426614174000";

    /**
     * 
     * @return テスト用のユーザー（a23e4567-e89b-12d3-a456-426614174000）
     */
    public static User createUser1() {
        User user = new User();
        user.setUuid(TEST_UUID1);
        user.setUserId("test_tarou");
        user.setName("テスト 太郎");
        user.setMail("test_tarou@example.com");
        user.setProfile("テスト");
        user.setBirthDate(LocalDate.of(1990, 1, 1));
        user.setIconImage(null);
        user.setPassword("test");
        return user;
    }

    /**
     * 
     * @return テスト用のユーザー（b23e4567-e89b-12d3-a456-426614174000）
     */
    public static User createUser2() {
        User user = new User();
        user.setUuid(TEST_UUID2);
        user.setUserId("test_hanako");
        user.setName("テスト 花子");
        user.setMail("test_tarou@example.com");
        user.setProfile("テスト");
        user.setBirthDate(LocalDate.of(1990, 1, 1));
        user.setIconImage(null);
        user.setPassword("test");
        return user;
    }

    /***
     * 
     * @param id
     * @param user
     * @param content
     * @param replyto
     * @param createdAt
     * @return
     */
    public static Post createPost(Long id, User user, String content, Integer replyto, LocalDateTime createdAt) {
        Post post = new Post();
        post.setId(id);
        post.setUserId(user);
        post.setReplyTo(replyto);
        post.setContent(content);
        post.setCreatedAt(createdAt);
        return post;
    }

    /**
     * 
     * @param favoriteUser
     * @param post
     * @param createdAt
     * @return
     */
    public static Favorite createFavoite(User favoriteUser, Post post, LocalDateTime createdAt) {
        FavoritePK favoritePK = new FavoritePK();
        favoritePK.setUserId(favoriteUser.getUuid());
        favoritePK.setPostId(post.getId());

        Favorite favorite = new Favorite();
        favorite.setUser(favoriteUser);
        favorite.setPost(post);
        favorite.setFavoritePK(favoritePK);
        favorite.setCreatedAt(createdAt);
        return favorite;
    }

    /**
     * 
     * @param uuid
     * @param postId
     * @param userId
     * @param userName
     * @param content
     * @param replyTo
     * @param favoreteCreatedAt
     * @param postCreatedAt
     * @return
     */
    public static UserFavoriteResponse createUserFavoriteResponse(String uuid, Long postId, String userId, String userName,
            String content, Integer replyTo, LocalDateTime favoreteCreatedAt, LocalDateTime postCreatedAt) {
        UserFavoriteResponse userFavoriteResponse = new UserFavoriteResponse();
        userFavoriteResponse.setUuid(uuid);
        userFavoriteResponse.setPostId(postId);
        userFavoriteResponse.setUserId(userId);
        userFavoriteResponse.setUserName(userName);
        userFavoriteResponse.setContent(content);
        userFavoriteResponse.setReplyTo(replyTo);
        userFavoriteResponse.setPostCreatedAt(postCreatedAt);
        userFavoriteResponse.setFavoriteCreatedAt(favoreteCreatedAt);
        return userFavoriteResponse;
    }
}
