package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.example.demo.model.Favorite;
import com.example.demo.model.FavoritePK;
import com.example.demo.model.Post;
import com.example.demo.model.PostWithFavorite;
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
        return createUser(TEST_UUID1, "test_tarou", "テスト 太郎", "test_tarou@example.com", "テスト", LocalDate.of(1990, 1, 1),
                null, "test");
    }

    /**
     *
     * @return テスト用のユーザー（b23e4567-e89b-12d3-a456-426614174000）
     */
    public static User createUser2() {
        return createUser(TEST_UUID2, "test_hanako", "テスト 花子", "test_tarou@example.com", "テスト",
                LocalDate.of(1990, 1, 1), null, "test");
    }

    /**
     * ユーザーを作成します。パラメータがnullの場合は乱数で生成します。
     *
     * @param uuid      UUID（nullの場合は乱数で生成）
     * @param userId    ユーザーID（nullの場合は乱数で生成）
     * @param name      名前（nullの場合は乱数で生成）
     * @param mail      メールアドレス（nullの場合は乱数で生成）
     * @param profile   プロフィール（nullの場合はデフォルト値）
     * @param birthDate 生年月日（nullの場合はデフォルト値）
     * @param iconImage アイコン画像（nullの場合はnull）
     * @param password  パスワード（nullの場合はデフォルト値）
     * @return 作成されたユーザー
     */
    public static User createUser(String uuid, String userId, String name, String mail,
            String profile, LocalDate birthDate, String iconImage, String password) {
        User user = new User();

        String randomSuffix = UUID.randomUUID().toString().substring(0, 8);

        user.setUuid(uuid != null ? uuid : UUID.randomUUID().toString());
        user.setUserId(userId != null ? userId : "user_" + randomSuffix);
        user.setName(name != null ? name : "テストユーザー_" + randomSuffix);
        user.setMail(mail != null ? mail : "test_" + randomSuffix + "@example.com");
        user.setProfile(profile != null ? profile : "テスト");
        user.setBirthDate(birthDate != null ? birthDate : LocalDate.of(1990, 1, 1));
        user.setIconImage(iconImage);
        user.setPassword(password != null ? password : "test");

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
     * @param             String content, Integer replyTo, LocalDateTime favoriteCreatedAt, LocalDateTime postCreatedAt) {

     * @param postCreatedAt
     * @return
     */
    public static UserFavoriteResponse createUserFavoriteResponse(String uuid, Long postId, String userId,
            String userName,
            String content, Integer replyTo, LocalDateTime favoriteCreatedAt, LocalDateTime postCreatedAt) {
        UserFavoriteResponse userFavoriteResponse = new UserFavoriteResponse();
        userFavoriteResponse.setUuid(uuid);
        userFavoriteResponse.setPostId(postId);
        userFavoriteResponse.setUserId(userId);
        userFavoriteResponse.setUserName(userName);
        userFavoriteResponse.setContent(content);
        userFavoriteResponse.setReplyTo(replyTo);
        userFavoriteResponse.setCreatedAt(postCreatedAt);
        userFavoriteResponse.setFavoriteCreatedAt(favoriteCreatedAt);
        return userFavoriteResponse;
    }

    /**
     * 
     * @param post
     * @param favorite
     * @return
     */
    public static PostWithFavorite createPostWithFavorite(Post post, boolean favorite){
        PostWithFavorite postWithFavorite = new PostWithFavorite();
        postWithFavorite.setPost(post);
        postWithFavorite.setFavorite(favorite);
        return postWithFavorite;
    }
}
