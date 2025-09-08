// package com.example.demo;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.when;

// import org.aspectj.lang.annotation.Before;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.bean.override.mockito.MockitoBean;
// import org.springframework.test.web.servlet.MockMvc;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import java.util.List;
// import java.util.ArrayList;

// import com.example.demo.controller.UserController;
// import com.example.demo.model.User;
// import com.example.demo.service.UserServiceImpl;

// @WebMvcTest(UserController.class)
// class UserControllerTest {

// 	@Autowired
// 	MockMvc mockMvc;

// 	@MockitoBean
// 	UserServiceImpl userServiceImpl;

// 	@Test
// 	void getUser() throws Exception {
// 		User user = new User();
// 		user.setAge(30);
// 		user.setName("yuki");
// 		user.setId((long) 1);

// 		List<User> users =  new ArrayList<>();
// 		users.add(user);
// 		when(userServiceImpl.getUsers()).thenReturn(users);
// 		mockMvc.perform(post("/all")
// 				.contentType(MediaType.APPLICATION_JSON))
// 				.andExpect(status().isOk())
// 				.andExpect(jsonPath("$[0].name").value("yuki"))
// 				.andExpect(jsonPath("$[0].age").value(30));

// 	}

// 	@Test
// 	void getUserById() throws Exception {
// 		User user = new User();
// 		user.setAge(30);
// 		user.setName("yuki");
// 		user.setId((long) 1);
// 				when(userServiceImpl.getUserById((long)1)).thenReturn(user);
// 		mockMvc.perform(post("/1")
// 				.contentType(MediaType.APPLICATION_JSON))
// 				.andExpect(status().isOk())
// 				.andExpect(jsonPath("$.name").value("yuki"))
// 				.andExpect(jsonPath("$.age").value(30));
// 	}
// }