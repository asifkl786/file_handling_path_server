package com.filehandling.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.filehandling.helper.FileUploadHelper;

@RestController
public class FileUploadControllerr {

	@Autowired
	private FileUploadHelper helper;
	
	 private static final List<String> ALLOWED_FILE_TYPES = Arrays.asList("image/jpeg", "image/png","application/pdf", "video/mp4");
	
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
		//System.out.println(file.getContentType());
		//System.out.println(file.getName());
		//System.out.println(file.getOriginalFilename());
		//System.out.println(file.getSize());
		 // Allowed file types
	   
		try {
			// Validation
			if(file.isEmpty()) {
				return new ResponseEntity<>("Please select a file",HttpStatus.BAD_REQUEST);
			}
			/*
			if(!file.getContentType().equals("image/jpeg")) {
				return new ResponseEntity<>("Other than Jpeg is allow ",HttpStatus.BAD_REQUEST);
			} */
			
			// Validate file type
	        String fileType = file.getContentType();
	        if (!ALLOWED_FILE_TYPES.contains(fileType)) {
	            throw new RuntimeException("File type not allowed. Allowed types: " + ALLOWED_FILE_TYPES);
	        }
			
			// File upload code 
			boolean fileUploaded = helper.uploadFile(file);
			if(fileUploaded) {
				return new ResponseEntity<>("File Successfully Uploaded",HttpStatus.CREATED);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>("Some Thing Went Wrong Try again", HttpStatus.BAD_REQUEST);
	}
} 
