package com.uxpsystems.assignment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uxpsystems.assignment.dto.UserDTO;
import com.uxpsystems.assignment.dto.UserResource;
import com.uxpsystems.assignment.entity.User;
import com.uxpsystems.assignment.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserServiceImpl userServiceImpl;
	
	
	public UserController(UserServiceImpl userServiceImpl ){
		this.userServiceImpl = userServiceImpl;
		
	}

	

	
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResource> getUser(@PathVariable long id){
		
		return ResponseEntity.ok(userServiceImpl.getUser(id));
		
		
	}
	
	@PostMapping(consumes = "application/json; charset=UTF-8" , produces = "application/json")
	public ResponseEntity<UserResource> addUser(@RequestBody UserDTO user){
		
		
		return ResponseEntity.ok(userServiceImpl.addUser(user));
		
	}
	
	@PutMapping
	public ResponseEntity<UserResource> updateUser(@RequestBody User user){
		userServiceImpl.updateUser(user);
		return ResponseEntity.ok(userServiceImpl.getUser(user.getId()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserResource> deleteUser(@PathVariable long id){
		return ResponseEntity.ok(userServiceImpl.deleteUser(id));
	}
}
