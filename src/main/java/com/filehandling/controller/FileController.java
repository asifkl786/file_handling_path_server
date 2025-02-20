package com.filehandling.controller;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.filehandling.payload.FileResponse;
import com.filehandling.service.FileService;
import com.filehandling.serviceImple.FileServiceImple;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	// Build Upload file REST API
	@PostMapping("/upload")
	public ResponseEntity<FileResponse> fileUpload(@RequestParam("file") MultipartFile file){
		logger.info("Recived Request to upload file Whose Name is :: {} ",file.getOriginalFilename());
		String fileName = fileService.fileUpload(path, file);
		return new ResponseEntity<>(new FileResponse(fileName,"file successfully uploaded !"),HttpStatus.OK);
	}
	
	// Build Download file REST API
	@GetMapping(value="/download-file/{fileName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadFile(@PathVariable("fileName") String fileName,HttpServletResponse response) throws IOException {
		logger.info("Recived Request to download file with name :: {}", fileName);
		InputStream inputStream = fileService.downloadFile(path,fileName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(inputStream, response.getOutputStream());
		
	}

}
