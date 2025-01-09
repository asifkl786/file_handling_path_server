package com.filehandling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filehandling.entity.User;
import com.filehandling.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	
	public User createUser(User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}

}
