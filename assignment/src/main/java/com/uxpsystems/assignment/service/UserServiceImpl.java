package com.uxpsystems.assignment.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.dao.UserRepository;
import com.uxpsystems.assignment.dto.UserDTO;
import com.uxpsystems.assignment.dto.UserResource;
import com.uxpsystems.assignment.entity.User;
import com.uxpsystems.assignment.exceptions.UserNotFoundException;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	   
	
	private UserRepository userRepository;
	
	private ModelMapper modelMapper;
	public UserServiceImpl( UserRepository userRepository, ModelMapper modelMapper ){
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
	}

	@Override
	public UserResource getUser(long id) {
	 logger.info("action=getUser status=start id={}", id);
		 User user = userRepository.findById(id).orElseThrow(
				()-> {
					logger.info("action=getUser found=false status=finished id={}", id);
				return new UserNotFoundException("User with id: "+id+"not found" );
				});
		 
		return modelMapper.map(user, UserResource.class);
	}

	@Override
	public UserResource addUser(UserDTO user) {
		
	//String password = 	BCrypt.hashpw(user.getPassword(),  BCrypt.gensalt());
	//user.setPassword(password);
		
	 logger.info("action=addUser status=start");
			
	 User savedUser = userRepository.save(modelMapper.map(user,User.class));
	 logger.info("action=addUser status=finished id={}",savedUser.getId() );

	 return modelMapper.map(savedUser, UserResource.class);
		
	}

	@Override
	public void updateUser(User user) {
		 logger.info("action=updateUser status=start ");
		 User tempUser = userRepository.findById(user.getId()).orElseThrow(
				()-> {
					logger.info("action=getUser found=false status=finished id={}", user.getId());
				return new UserNotFoundException("User with id: "+ user.getId()+" not found" );
				});
	
		 userRepository.updateUser(user.getId(), user.getUserName(), user.getPassword(), user.getStatus());
		 userRepository.flush();
		 logger.info("action=updateUser status=finished id={}",user.getId());		
	
	}

	@Override
	public UserResource deleteUser(long id) {
		 logger.info("action=deleteUser status=start id={}", id);
		 User user = userRepository.findById(id).orElseThrow(
					()-> {
						logger.info("action=getUser found=false status=finished id={}", id);
					return new UserNotFoundException("User with id: "+id+"not found" );
					});
		 userRepository.deleteById(id);
		 logger.info("action=deleteUser found=false status=finished id={}", id);
		 return modelMapper.map(user, UserResource.class);
	}

}
