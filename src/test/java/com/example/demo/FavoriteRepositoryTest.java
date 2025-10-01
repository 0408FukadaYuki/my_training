package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import com.github.database.rider.spring.api.DBRider;
import com.example.demo.model.Favorite;
import com.example.demo.model.Post;
import com.example.demo.model.User;
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
        User user = TestUtil.createUser1();
        Post post = TestUtil.createPost((long) 2, user, "これはJunitのテストです", null, null);
        Favorite expectedFavorite = TestUtil.createFavoite(user, post, null);

        User findUser = new User();
        findUser.setUuid(TestUtil.TEST_UUID1);
        Iterable<Favorite> favorite = favoriteRepository.findByUser(findUser);
        favorite.forEach(f -> {
            assertEquals(expectedFavorite.getFavoritePK(), f.getFavoritePK());
            assertEquals(expectedFavorite.getPost().getId(), f.getPost().getId());
            assertEquals(expectedFavorite.getUser().getUuid(), f.getUser().getUuid());
        });
    }

}
