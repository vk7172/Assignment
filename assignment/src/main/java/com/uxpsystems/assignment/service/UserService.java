package com.uxpsystems.assignment.service;

import com.uxpsystems.assignment.dto.UserDTO;
import com.uxpsystems.assignment.dto.UserResource;
import com.uxpsystems.assignment.entity.User;

public interface UserService {
	
	public UserResource getUser(long id) ;

	public UserResource addUser(UserDTO user);
	
	public void updateUser(User user);
	
	public UserResource deleteUser(long id);
}
