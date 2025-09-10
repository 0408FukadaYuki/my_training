package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import com.github.database.rider.spring.api.DBRider;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

// TODO: 開発用とテスト用とで接続先DBを分ける
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")

@Sql(scripts = "classpath:testdata/test-data.sql", 
	executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	void getUserById() throws Exception {
		User expectUser = new User();
		expectUser.setUuid("a23e4567-e89b-12d3-a456-426614174000");
		expectUser.setUserId("test_tarou");
		expectUser.setName("テスト 太郎");
		expectUser.setMail("tarou@example.com");
		expectUser.setProfile("テストです");
		String dateString = "1985-05-15 00:00:00.0";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date date = sdf.parse(dateString);
		expectUser.setBirthDate(date);
		expectUser.setIconImage("");
		expectUser.setPassword("ef92b778bafe771e89245b89ecbcf23e9a8a0d53a71f80626e3e408559caa046");

		Optional<User> user = userRepository.findById("a23e4567-e89b-12d3-a456-426614174000");
        User actualUser = user.get();
		assertEquals(expectUser, actualUser);

	}
}
