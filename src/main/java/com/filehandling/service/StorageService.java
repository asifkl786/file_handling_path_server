package com.filehandling.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.filehandling.entity.FileData;
import com.filehandling.entity.ImageData;
import com.filehandling.exception.ResourceNotFoundException;
import com.filehandling.repository.FileDataRepository;
import com.filehandling.repository.StorageRepository;
import com.filehandling.util.ImageUtils;


@Service
public class StorageService {

	
	@Autowired
    private StorageRepository repository;

    @Autowired
    private FileDataRepository fileDataRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);

    //  private final String FOLDER_PATH="/Users/javatechie/Desktop/MyFIles/";
    
    //  private final String FOLDER_PATH="/src/main/webapp/picture/";C:\Users\Asif Khan\OneDrive\Desktop\myfiles
    //  private static final String UPLOAD_DIR = "C:/uploads";"C:\Users\Asif Khan\OneDrive\Desktop\my files"
   // "C:\Users\Public\Documents"
    // Define the directory where files will be saved
    private static final String UPLOAD_DIR ="C:\\Users\\Public\\MyFile\\images";
    
    
    // Upload image to database
    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        logger.info("File Successfully Upload With {} ::", file.getOriginalFilename());
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
 
    
    // Upload image as fileSystem
    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath=UPLOAD_DIR+file.getOriginalFilename();

        FileData fileData=fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    
    // Download image as in fileSystem
    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        logger.info("File Download Successfully with Name:: {}",fileName);
        return images;
    }   

    
    
    // Download image By id
    public byte[] downloadImageFromFileSystemm(Integer id) throws IOException {
    	// Fetch the FileData object
        FileData fileData = fileDataRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("File does not exist with the given ID: " + id));
        
       // Validate filePath
        String filePath = fileData.getFilePath(); // Assuming fileData is not Optional
        if (filePath == null || filePath.isEmpty()) {
            throw new ResourceNotFoundException("File path is not set for the given ID: " + id);
        }
        
        logger.info("File Successfully download with :: {}", fileData);
        // Read the file as bytes
        return Files.readAllBytes(new File(filePath).toPath());
        
         }
}
