package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.Optional;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yml")
@Sql(scripts = "classpath:testdata/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	void getUserById() throws Exception {
		User createUserInfo = new User();
		createUserInfo.setUuid("018ffc72-5231-7262-bcf6-1434e89e9e0c");
		createUserInfo.setUserId("testUser");
		createUserInfo.setIconImage(null);
		createUserInfo.setName("testUserName");
		createUserInfo.setMail("test@test.com");
		createUserInfo.setProfile("これはUserContollerのテストです");
		createUserInfo.setPassword("b94d27b9934d3e08a52e52d7da7dabfa");
		LocalDate birthDate = LocalDate.of(1997, 04, 8);
		createUserInfo.setBirthDate(birthDate);

		userRepository.save(createUserInfo);

		Optional<User> user = userRepository.findById("018ffc72-5231-7262-bcf6-1434e89e9e0c");
		User actualUser = user.get();
		assertEquals("018ffc72-5231-7262-bcf6-1434e89e9e0c", actualUser.getUuid());
		assertEquals("testUser", actualUser.getUserId());
		assertEquals("testUserName", actualUser.getName());
		assertEquals("test@test.com", actualUser.getMail());
		assertEquals("これはUserContollerのテストです", actualUser.getProfile());
		assertEquals(birthDate, actualUser.getBirthDate());
		assertEquals(null, actualUser.getIconImage());
		assertEquals("b94d27b9934d3e08a52e52d7da7dabfa", actualUser.getPassword());

	}
}
