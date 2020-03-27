package com.uxpsystems.assignment.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.uxpsystems.assignment.dao.UserRepository;
import com.uxpsystems.assignment.dto.UserDTO;
import com.uxpsystems.assignment.dto.UserResource;
import com.uxpsystems.assignment.entity.Status;
import com.uxpsystems.assignment.entity.User;
import com.uxpsystems.assignment.exceptions.UserNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	UserServiceImpl userService;
	
	@Mock
	UserRepository userRepository;
	
	@Before
	public void setUp(){
		 ModelMapper modelMapper = new ModelMapper();
		 userService = new UserServiceImpl(userRepository, modelMapper);
		
	}
	
	 @Test
	    public void should_return_user_based_on_input_id() throws Exception {
	        User user = new User(1L, "TestUser1", "password", Status.ACTIVATED);
	        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

	        UserResource userResource = userService.getUser(user.getId());

	        verifyData(user, userResource);
	    }
	 
	 @Test(expected = UserNotFoundException.class)
	    public void should_return_not_found_error_if_user_does_not_exists() {
		 Long id = 5L;
		 when(userRepository.findById(id)).thenReturn(Optional.empty());
		 UserResource userResource = userService.getUser(id);
	 }
	 
	 
	 @Test
	    public void should_save_user() throws Exception {
		  User user = new User(1L, "TestUser1", "password", Status.ACTIVATED);
		  UserDTO userDTO = new UserDTO("TestUser1", "password", "ACTIVATED");
	    	  when(userRepository.save(Mockito.any(User.class))).thenReturn(user);


	        UserResource userResource = userService.addUser(userDTO);

	        verifyData(user, userResource);
	    }
	 
	 @Test
	    public void should_update_user() throws Exception {
		 	User user = new User(1L, "TestUser1", "password", Status.ACTIVATED);
		 	  when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
  		 	doNothing().when(userRepository).updateUser(1L, "TestUser1", "password", Status.ACTIVATED);

	        userService.updateUser(user);

	        
	    }
	 
	 	@Test
	    public void should_delete_user() throws Exception {
		   User user = new User(1L, "TestUser1", "password", Status.ACTIVATED);
		   when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
		   doNothing().when(userRepository).deleteById(user.getId());
		   UserResource userResource = userService.deleteUser(user.getId());

	        verifyData(user, userResource);
	    }

	 
	   

	private void verifyData(User user, UserResource userResource) {

		assertEquals(user.getUserName(), userResource.getUserName());
		assertEquals(user.getPassword(),userResource.getPassword());
		assertEquals(user.getStatus().toString(),userResource.getStatus());
		assertEquals(user.getCreatedTime(),userResource.getCreatedTime());
	}
	
	

}
