package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.demo.exception.PostNotCreatedException;
import com.example.demo.exception.PostNotDeletedException;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.model.Post;
import com.example.demo.model.PostWithFavorite;
import com.example.demo.model.User;
import com.example.demo.model.request.CreatePostRequest;
import com.example.demo.model.response.GetAllPostResponse;
import com.example.demo.repository.FavoriteRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @MockitoBean
    private PostRepository postRepository;

    @MockitoBean
    private FavoriteRepository favoriteRepository;

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

    @Test
    void testCreatePostThrowException() {
        CreatePostRequest createPostRequest = new CreatePostRequest();
        createPostRequest.setUserId("23e4567-e89b-12d3-a456-426614174000");
        createPostRequest.setContent("Contollerのテストです");
        createPostRequest.setReplyTo(null);
        DataAccessException dataAccessException = new DataAccessException("error") {
        };
        when(postRepository.save(any())).thenThrow(dataAccessException);
        PostNotCreatedException exception = assertThrows(PostNotCreatedException.class,
                () -> postService.createPost(createPostRequest));
        assertEquals(exception.getMessage(), "投稿を作成できませんでした。");
    }

    @Test
    void testDeletePost() {
        Post post = new Post();
        post.setId((long) 2);
        Optional<Post> mockValue = Optional.of(post);
        when(postRepository.findById((long) 2)).thenReturn(mockValue);
        assertDoesNotThrow(() -> postService.deletePost((long) 2));
    }

    @Test
    void testDeletePostThrowsExceptionOfPostRepository() {
        Post post = new Post();
        post.setId((long) 2);
        Optional<Post> mockValue = Optional.of(post);
        when(postRepository.findById((long) 2)).thenReturn(mockValue);
        doThrow(new DataAccessException("error") {
        }).when(postRepository).deleteById((long) 2);
        PostNotDeletedException exception = assertThrows(PostNotDeletedException.class,
                () -> postService.deletePost((long) 2));
        assertEquals(exception.getMessage(), "投稿を削除できませんでした。");
    }

    @Test
    void testDeletePostThrowsExceptionOfFavoriteRepository() {
        Post post = new Post();
        post.setId((long) 2);
        Optional<Post> mockValue = Optional.of(post);
        when(postRepository.findById((long) 2)).thenReturn(mockValue);
        doThrow(new DataAccessException("error") {
        }).when(favoriteRepository).deleteByPost(post);
        PostNotDeletedException exception = assertThrows(PostNotDeletedException.class,
                () -> postService.deletePost((long) 2));
        assertEquals(exception.getMessage(), "投稿を削除できませんでした。");
    }

    @Test
    void testDeletePostThrowsExceptionOfFindById() {
        Post post = new Post();
        post.setId((long) 2);
        Optional<Post> mockValue = Optional.empty();
        when(postRepository.findById((long) 2)).thenReturn(mockValue);
        PostNotDeletedException exception = assertThrows(PostNotDeletedException.class,
                () -> postService.deletePost((long) 2));
        assertEquals(exception.getMessage(), "指定された投稿が見つかりませんでした。");
    }

    @Test
    void testFindAllPost() {
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

        List<GetAllPostResponse> expectedResponses = new ArrayList<>(List.of(response1, response2));

        User user1 = TestUtil.createUser1();
        Post post1 = TestUtil.createPost((long)1, user1, "これはJunitのテストです", null, localDateTime);

        User user2 = TestUtil.createUser2();
        Post post2 = TestUtil.createPost((long)2, user2, "これはJunitのテストです", null, localDateTime);

        List<PostWithFavorite> mockResponse = new ArrayList<>(List.of(TestUtil.createPostWithFavorite(post1,false), TestUtil.createPostWithFavorite(post2,false)));

        when(postRepository.findAllPost(TestUtil.TEST_UUID1)).thenReturn(mockResponse);

        List<GetAllPostResponse> actualResponse = postService.findAllPost(TestUtil.TEST_UUID1);

        assertEquals(expectedResponses, actualResponse);
    }

    @Test
    void testFindAllPostThrowsException() {
        DataAccessException dataAccessException = new DataAccessException("error") {
        };
        when(postRepository.findAllPost(TestUtil.TEST_UUID1)).thenThrow(dataAccessException);
        PostNotFoundException exception = assertThrows(PostNotFoundException.class, () -> postService.findAllPost(TestUtil.TEST_UUID1));
        assertEquals(exception.getMessage(), "投稿を取得できませんでした。");
    }
}
