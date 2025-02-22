package com.filehandling.controller;

import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.filehandling.entity.FileUpload;
import com.filehandling.response.FileUploadResponse;
import com.filehandling.service.FileUploadService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/files")
public class FileUploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@Autowired
    private FileUploadService uploadService;

	
	// Build Upload file REST API 
    @PostMapping("/upload")
    public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
    	logger.info("Recived Request to upload file with Name :: {}",file.getOriginalFilename());
        FileUpload attachment = null;
        String downloadUrl = "";
        attachment = uploadService.uploadFile(file);
        downloadUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getId())
                .toUriString();
        return new FileUploadResponse(attachment.getFileName(), downloadUrl, file.getContentType(), file.getSize());
    }

    // Build download file REST API
    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> download(@PathVariable("fileId")String fileId) throws Exception {
    	logger.info("Recived Request to download file with Name :: {}",fileId);
        FileUpload fileUpload = null;
        fileUpload = uploadService.downloadFile(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileUpload.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "fileUpload; filename=\""+ fileUpload.getFileName()
                        +"\"").body(new ByteArrayResource(fileUpload.getData()));
    }

}
