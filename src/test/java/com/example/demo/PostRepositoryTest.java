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
        List<Post> response = postRepository.findAll();
        List<Post> actual = new ArrayList<>();
        response.forEach(actual::add);
        assertEquals(13, actual.size());
        assertEquals(1, actual.get(0).getId());
        assertEquals("323e4567-e89b-12d3-a456-426614174002", actual.get(0).getUserId().getUuid());
        assertEquals("初めての投稿です。よろしくお願いします。", actual.get(0).getContent());
        assertEquals(null, actual.get(0).getReplyTo());
        assertEquals(2, actual.get(1).getId());
        assertEquals("223e4567-e89b-12d3-a456-426614174001", actual.get(1).getUserId().getUuid());
        assertEquals("2回目の投稿です。", actual.get(1).getContent());
        assertEquals(null, actual.get(1).getReplyTo());
        assertEquals(3, actual.get(2).getId());
        assertEquals("123e4567-e89b-12d3-a456-426614174000", actual.get(2).getUserId().getUuid());
        assertEquals("3回目の投稿です。", actual.get(2).getContent());
        assertEquals(null, actual.get(2).getReplyTo());
    }

    @Test
    @DBRider
    void testDeletePost() throws Exception{
        postRepository.deleteById((long)1);
        Optional<Post> acutal = postRepository.findById((long)1);
        assertTrue(acutal.isEmpty());
    }
}
