package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import com.example.demo.controller.UserController;
import com.example.demo.model.CreateUserRequest;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
class UserControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
    ObjectMapper objectMapper;

	@MockitoBean
	UserService userService;

	@Test
	void testCreateUser() throws Exception {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUserId("testUser");
        createUserRequest.setIconImage(null);
        createUserRequest.setName("testUserName");
        createUserRequest.setMail("test@test.com");
        createUserRequest.setProfile("これはUserContollerのテストです");
        createUserRequest.setPassword("1234test5678");
        LocalDate birthDate = LocalDate.of(1997, 04, 8);
		createUserRequest.setBirthDate(birthDate);
        String request = objectMapper.writeValueAsString(createUserRequest);
		mockMvc.perform(post("/user/create")
				.contentType(MediaType.APPLICATION_JSON)
                .content(request))
				.andExpect(status().isOk());
    }
}