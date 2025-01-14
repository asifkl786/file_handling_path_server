package com.filehandling.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.filehandling.entity.Image;
import com.filehandling.repository.ImageRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {
	
	private static final Logger logger = LoggerFactory.getLogger(ImageService.class);

    @Value("${image.upload.dir}")
    private String uploadDir;

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image uploadImage(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String sanitizedFilename = originalFilename.replaceAll("[^a-zA-Z0-9\\.\\-_]", "_");
        String uniqueFilename = UUID.randomUUID() + "_" + sanitizedFilename;
        String filePath = uploadDir + File.separator + uniqueFilename;

        
        
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // Create directory if it doesn't exist
        }
        
        // Save file to filesystem
      //  file.transferTo(new File(filePath));
        try {
            file.transferTo(new File(filePath));
            logger.info("File successfully saved: " + filePath);
        } catch (IOException e) {
            logger.error("Failed to save file", e);
            throw new RuntimeException("File upload failed", e);
        }

        // Save metadata to database
        Image image = new Image();
        image.setName(originalFilename);
        image.setUrl(uniqueFilename);
        image.setUploadTime(LocalDateTime.now());
        return imageRepository.save(image);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
}

