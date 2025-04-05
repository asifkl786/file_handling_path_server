package com.filehandling.service;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.filehandling.entity.ImageData;
import com.filehandling.repository.ImageDataRepository;
import com.filehandling.util.ImageUtils;

@Service
public class ImageDataService {

	
	 private static final Logger logger = LoggerFactory.getLogger(ImageDataService.class);

	 
	    @Autowired
	    private ImageDataRepository repository;
	 
	    // Upload image to database
	    public String uploadImage(MultipartFile file) throws IOException {
	        ImageData imageData = repository.save(ImageData.builder()
	                .name(file.getOriginalFilename())
	                .type(file.getContentType())
	                .imageData(ImageUtils.compressImage(file.getBytes())).build());
	        logger.info("File Successfully Upload With Name :: {}", file.getOriginalFilename());
	        System.out.println("Image size: " + imageData.getImageData() + " bytes");

	        if (imageData != null) {
	            return "file uploaded successfully : " + file.getOriginalFilename();
	        }
	        return null;
	    }

	    // Download image from database only one image can download
	    public byte[] downloadImage(String fileName) {
	        Optional<ImageData> dbImageData = repository.findByName(fileName);
	        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
	        logger.info("File Successfully Download",images);
	        return images;
	    }
}
