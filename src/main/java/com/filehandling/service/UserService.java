package com.filehandling.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filehandling.entity.User;
import com.filehandling.repository.UserRepository;

@Service
public class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserRepository userRepository;
	
	// Create User 
	public User createUser(User user) {
		User savedUser = userRepository.save(user);
		logger.info("User Successfully Created With Name :: {}",user.getName());
		return savedUser;
	}

}
