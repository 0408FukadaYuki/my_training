package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDateTime;
import java.util.Optional;
import com.github.database.rider.spring.api.DBRider;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;

@DataJpaTest
class PostRepositoryTest {

	@Autowired
	private PostRepository postRepository;

	@Test
	@DBRider
	void testCreatePost() throws Exception {
        User user = new User();
        user.setUuid("123e4567-e89b-12d3-a456-426614174000");

        //期待値の作成
		Post expectPost = new Post();
        expectPost.setContent("これはPost作成のテストです");
        expectPost.setId((long)4);
        expectPost.setUserId(user);
        LocalDateTime now = LocalDateTime.now();
        expectPost.setCreatedAt(now);
        

        Post createPostInfo = new Post();
        createPostInfo.setContent("これはPost作成のテストです");
        createPostInfo.setUserId(user);
        postRepository.save(createPostInfo);

		Optional<Post> post = postRepository.findById((long)4);
        Post resultPost = post.get();
		assertEquals(expectPost.getId(), resultPost.getId());
        assertEquals(expectPost.getUserId(), resultPost.getUserId());
        assertEquals(expectPost.getReplyTo(), resultPost.getReplyTo());
        assertEquals(expectPost.getContent(), resultPost.getContent());
        // ミリ秒単位の時間がずれてしまう
        // assertEquals(expectPost.getCreatedAt(), resultPost.getCreatedAt());
	}
}
