package com.filehandling.service;

import com.filehandling.entity.FileEntityy;
import com.filehandling.repository.FileEntityyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

@Service
public class FileEntityyService {

    @Autowired
    private FileEntityyRepository fileRepository;

    private final Path fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();

    // Allowed file types
    private static final List<String> ALLOWED_FILE_TYPES = Arrays.asList("image/jpeg", "image/png","application/pdf", "video/mp4");

    // Maximum file size (10MB)
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB

    public void FileService() {
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create upload directory!", ex);
        }
    }

    public FileEntityy storeFile(MultipartFile file) {
        // Validate file type
        String fileType = file.getContentType();
        if (!ALLOWED_FILE_TYPES.contains(fileType)) {
            throw new RuntimeException("File type not allowed. Allowed types: " + ALLOWED_FILE_TYPES);
        }

        // Validate file size
        if (file.getSize() > MAX_FILE_SIZE) {
            throw new RuntimeException("File size exceeds the maximum limit of 10MB.");
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new RuntimeException("Invalid file path!");
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            FileEntityy fileEntity = new FileEntityy();
            fileEntity.setFileName(fileName);
            fileEntity.setFileType(fileType);
            fileEntity.setFilePath(targetLocation.toString());

            return fileRepository.save(fileEntity);
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName, ex);
        }
    }

    public FileEntityy getFile(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found!"));
    }
}
