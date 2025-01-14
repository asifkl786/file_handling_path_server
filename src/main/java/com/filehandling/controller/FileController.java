package com.filehandling.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import com.filehandling.entity.FileEntity;
import com.filehandling.service.FileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@RestController
@RequestMapping("/api/files")
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	FileService fileService;

	// Build Upload file REST API 
    @PostMapping("/upload")
    public ResponseEntity<FileEntity> uploadFile(@RequestParam("file") MultipartFile file) {
    	logger.info("Recived Request to upload file with :: {}",file);
    	System.out.println("File received: " + file.getOriginalFilename());
        try {
            FileEntity savedFile = fileService.uploadFile(file);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFile);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Build download file REST API
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
    	logger.info("Recived Request to download file with id :: {}",id);
        Optional<FileEntity> optionalFile = fileService.getFile(id);

        if (optionalFile.isPresent()) {
            FileEntity fileEntity = optionalFile.get();
            try {
                File file = new File(fileEntity.getFilePath());
                byte[] fileContent = Files.readAllBytes(file.toPath());

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                        .body(fileContent);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

