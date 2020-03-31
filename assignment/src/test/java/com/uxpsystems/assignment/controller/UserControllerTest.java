package com.uxpsystems.assignment.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uxpsystems.assignment.dto.UserDTO;
import com.uxpsystems.assignment.dto.UserResource;
import com.uxpsystems.assignment.entity.Status;
import com.uxpsystems.assignment.entity.User;
import com.uxpsystems.assignment.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserServiceImpl userService;

	private final ObjectMapper mapper = new ObjectMapper();

	private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+0000");

	@Before
	public void setUp() {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	@Test
	@WithMockUser(username = "admin", password = "password", roles = "USER")
	public void should_return_user_based_on_input_id() throws Exception {
		User user = new User(1L, "TestUser1", "password", Status.ACTIVATED);
		UserResource userResource = new UserResource(1L, "TestUser1", "password", "ACTIVATED", new Date());

		BDDMockito.given(userService.getUser(user.getId())).willReturn(userResource);

		final ResultActions result = mockMvc.perform(get("/user/" + user.getId()).contentType("application/json"))
				.andExpect(status().isOk());

		verifyJson(result, userResource, "");
	}

	@Test
	@WithMockUser(username = "admin", password = "password", roles = "USER")
	public void should_save_user() throws Exception {
		User user = new User(1L, "TestUser1", "password", Status.ACTIVATED);
		UserDTO userDTO = new UserDTO("TestUser1", "password", "ACTIVATED");
		UserResource userResource = new UserResource(1L, "TestUser1", "password", "ACTIVATED", new Date());
		BDDMockito.given(userService.addUser(userDTO)).willReturn(userResource);

		final ResultActions result = mockMvc
				.perform(post("/user").contentType("application/json").content(mapper.writeValueAsString(userDTO)))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", password = "password", roles = "USER")
	public void should_delete_user() throws Exception {
		UserDTO userDTO = new UserDTO("TestUser1", "password", "ACTIVATED");
		UserResource userResource = new UserResource(1L, "TestUser1", "password", "ACTIVATED", new Date());
		BDDMockito.given(userService.deleteUser(1L)).willReturn(userResource);

		final ResultActions result = mockMvc.perform(delete("/user/1").contentType("application/json"))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", password = "password", roles = "USER")
	public void should_update_user() throws Exception {
		User user = new User(1L, "TestUser1", "password", Status.ACTIVATED);

		UserDTO userDTO = new UserDTO("TestUser1", "password", "ACTIVATED");
		UserResource userResource = new UserResource(1L, "TestUser1", "password", "ACTIVATED", new Date());
		BDDMockito.willDoNothing().given(userService).updateUser(user);

		final ResultActions result = mockMvc
				.perform(delete("/user/1").contentType("application/json").content(mapper.writeValueAsString(userDTO)))
				.andExpect(status().isOk());
	}

	private void verifyJson(final ResultActions action, final UserResource user, String path) throws Exception {
		action
				// .andExpect(jsonPath(path + "id", is(user.getId())))
				.andExpect(jsonPath(path + "userName", is(user.getUserName())))
				.andExpect(jsonPath(path + "password", is(user.getPassword())))
				.andExpect(jsonPath(path + "status", is(user.getStatus().toString())));

	}

}
