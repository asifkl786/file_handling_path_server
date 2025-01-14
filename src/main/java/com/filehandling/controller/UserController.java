package com.filehandling.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.filehandling.entity.User;
import com.filehandling.service.UserService;

@RestController
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/webapp/images";
	
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@ModelAttribute User user, @RequestParam("image") MultipartFile file) throws IOException {
		logger.info("Recived Request to upload file with :: {}",file);
		String orignalFileName = file.getOriginalFilename();
		Path fileNameAndPath = Paths.get(uploadDirectory,orignalFileName);
		Files.write(fileNameAndPath, file.getBytes());
		user.setProfilePicture(orignalFileName);
		User userr = userService.createUser(user);
		return new ResponseEntity<>(userr,HttpStatus.CREATED);
	}

}
