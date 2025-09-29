package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import com.github.database.rider.spring.api.DBRider;
import com.example.demo.model.Favorite;
import com.example.demo.model.FavoritePK;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.model.request.CreateFavoriteRequest;
import com.example.demo.repository.FavoriteRepository;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@Sql(scripts = "classpath:testdata/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class FavoriteRepositoryTest {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Test
    @DBRider
    void testFindByUser() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(1990, 1, 1, 0, 0, 0);
        Favorite expectavorite = new Favorite();
        Post post = new Post();
        post.setId((long) 2);
        post.setContent("これはJunitのテストです");
        post.setReplyTo(null);
        post.setCreatedAt(localDateTime);
        User postUser = new User();
        postUser.setUuid("b23e4567-e89b-12d3-a456-426614174000");
        postUser.setUserId("test_hanako");
        postUser.setName("テスト 花子");
        post.setUserId(postUser);
        FavoritePK favoritePK = new FavoritePK();
        favoritePK.setPostId((long) 2);
        favoritePK.setUserId("a23e4567-e89b-12d3-a456-426614174000");
        User user = new User();
        user.setUuid("a23e4567-e89b-12d3-a456-426614174000");
        user.setUserId("test_tarou");
        user.setName("テスト 太郎");
        expectavorite.setFavoritePK(favoritePK);
        expectavorite.setPost(post);
        expectavorite.setUser(user);
        expectavorite.setCreatedAt(localDateTime);

        CreateFavoriteRequest createFavoriteRequest = new CreateFavoriteRequest();
        createFavoriteRequest.setUuid("a23e4567-e89b-12d3-a456-426614174000");
        createFavoriteRequest.setPostId((long) 5);

        User findUser = new User();
        findUser.setUuid("a23e4567-e89b-12d3-a456-426614174000");
        Iterable<Favorite> favorite = favoriteRepository.findByUser(findUser);
        favorite.forEach(f -> {
            assertEquals(expectavorite.getFavoritePK(), f.getFavoritePK());
            assertEquals(expectavorite.getPost().getId(), f.getPost().getId());
            assertEquals(expectavorite.getUser().getUuid(), f.getUser().getUuid());
        });
    }

}
