package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.github.database.rider.spring.api.DBRider;
import com.example.demo.model.Post;
import com.example.demo.model.PostWithFavorite;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@Sql(scripts = "classpath:testdata/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    @DBRider
    void testCreatePost() throws Exception {
        User user = new User();
        user.setUuid("a23e4567-e89b-12d3-a456-426614174000");

        // 期待値の作成
        Post expectPost = new Post();
        expectPost.setContent("これはPost作成のテストです");
        expectPost.setId((long) 14);
        expectPost.setUserId(user);
        LocalDateTime now = LocalDateTime.now();
        expectPost.setCreatedAt(now);

        Post createPostInfo = new Post();
        createPostInfo.setContent("これはPost作成のテストです");
        createPostInfo.setUserId(user);
        postRepository.save(createPostInfo);

        Optional<Post> post = postRepository.findById((long) 14);
        Post resultPost = post.get();
        assertEquals(expectPost.getId(), resultPost.getId());
        assertEquals(expectPost.getUserId().getUuid(), resultPost.getUserId().getUuid());
        assertEquals(expectPost.getReplyTo(), resultPost.getReplyTo());
        assertEquals(expectPost.getContent(), resultPost.getContent());
        assertEquals(expectPost.getCreatedAt().truncatedTo(ChronoUnit.SECONDS),
                resultPost.getCreatedAt().truncatedTo(ChronoUnit.SECONDS));
    }

    @Test
    @DBRider
    void testFindAllPost() throws Exception {
        List<PostWithFavorite> response = postRepository.findAllPost(TestUtil.TEST_UUID1);
        List<PostWithFavorite> actual = new ArrayList<>();
        response.forEach(actual::add);
        assertEquals(13, actual.size());
        assertEquals(10, actual.get(0).getPost().getId());
        assertEquals("323e4567-e89b-12d3-a456-426614174002", actual.get(0).getPost().getUserId().getUuid());
        assertEquals("これからよろしくお願いします。", actual.get(0).getPost().getContent());
        assertEquals(null, actual.get(0).getPost().getReplyTo());
        assertEquals(false,actual.get(0).isFavorite());
        assertEquals(9, actual.get(1).getPost().getId());
        assertEquals("223e4567-e89b-12d3-a456-426614174001", actual.get(1).getPost().getUserId().getUuid());
        assertEquals("返信待ってます。", actual.get(1).getPost().getContent());
        assertEquals(null, actual.get(1).getPost().getReplyTo());
        assertEquals(false,actual.get(1).isFavorite());
        assertEquals(8, actual.get(2).getPost().getId());
        assertEquals("423e4567-e89b-12d3-a456-426614174003", actual.get(2).getPost().getUserId().getUuid());
        assertEquals("皆さんの投稿楽しみにしています。", actual.get(2).getPost().getContent());
        assertEquals(null, actual.get(2).getPost().getReplyTo());
        assertEquals(false,actual.get(2).isFavorite());
    }

    @Test
    @DBRider
    void testDeletePost() throws Exception{
        postRepository.deleteById((long)1);
        Optional<Post> actual = postRepository.findById((long)1);
        assertTrue(actual.isEmpty());
    }
}
