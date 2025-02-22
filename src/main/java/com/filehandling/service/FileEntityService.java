package com.filehandling.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.filehandling.entity.FileEntity;
import com.filehandling.repository.FileEntityRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class FileEntityService {
	
	private static final Logger logger = LoggerFactory.getLogger(FileEntityService.class);

    @Value("${file.upload-dir}")
    private String uploadDir;

     @Autowired
     FileEntityRepository fileRepository;

    // Upload file  
    public FileEntity uploadFile(MultipartFile file) throws IOException {
        // Create directory if not exists
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Save file to disk (file.upload-dir=C:/Users/Public/MyFiles/file)
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.write(filePath, file.getBytes());

        // Save file metadata to database
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(file.getOriginalFilename());
        fileEntity.setType(file.getContentType());
        fileEntity.setFilePath(filePath.toString());
       FileEntity savedFile = fileRepository.save(fileEntity);
       logger.info("{}:: File Successfully upload",fileName);
       return savedFile;
    }

    // Download file By Id
    public Optional<FileEntity> getFile(Long id) {
    	logger.info("File  Successfully fetch with id :: {}",id);
        return fileRepository.findById(id);
    }
}

