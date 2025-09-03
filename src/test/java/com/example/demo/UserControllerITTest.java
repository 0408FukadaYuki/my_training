package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import java.util.ArrayList;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import com.example.demo.service.UserServiceImpl;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DBUnit(caseSensitiveTableNames = false, schema = "test", url= "jdbc:mysql://localhost:3306/test", user = "root", password = "Yokohama14" )
class UserControllerITTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	void getUser() throws Exception {
		mockMvc.perform(post("/all")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("yuki"))
				.andExpect(jsonPath("$[0].age").value(30));

	}

	@Test
	void getUserById() throws Exception {
		mockMvc.perform(post("/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("yuki"))
				.andExpect(jsonPath("$.age").value(30));
	}
}