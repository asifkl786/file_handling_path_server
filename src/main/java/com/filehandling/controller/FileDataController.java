package com.filehandling.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.filehandling.service.FileDataService;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v2/files")
public class FileDataController {
	
private static final Logger logger = LoggerFactory.getLogger(FileDataController.class);
	
	@Autowired
	private FileDataService service;
	

	//  Build Upload image REST API in file System
	@PostMapping("/fileSystem/upload")
	public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("file")MultipartFile file) throws IOException {
		logger.info("Recived Request to upload file with Name :: {}",file.getOriginalFilename());
		String uploadImage = service.uploadImageToFileSystem(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

	/*
	// Build Download image REST API as file System
	@GetMapping("fileSystem/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws IOException {
		logger.info("Recived Request to download file with :: {}",fileName);
		byte[] imageData=service.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	} */ 
	
	// Build Download image REST API as file System
	@GetMapping("/fileSystem/{id}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable Integer id) throws IOException{
		logger.info("Recived Request to download file with id :: {}",id);
		byte[] imageData = service.downloadImageFromFileSystemm(id);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);
	}

}
