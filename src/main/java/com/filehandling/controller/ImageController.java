package com.filehandling.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.filehandling.entity.Image;
import com.filehandling.service.ImageService;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = "*")
public class ImageController {
	
	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Value("${image.upload.dir}")
    private String uploadDir;

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    
    // Build Upload Image REST API
    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file) {
    	logger.info("Recived Request to upload file with :: {}",file.getOriginalFilename());
        try {
            Image uploadedImage = imageService.uploadImage(file);
           // return ResponseEntity.ok(uploadedImage);
            return new ResponseEntity<>(uploadedImage, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Build get all Image REST API 
    @GetMapping
    public ResponseEntity<List<Image>> getAllImages() {
    	logger.info("Recived Request to get All file ");
        return ResponseEntity.ok(imageService.getAllImages());
    }
}

