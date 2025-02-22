package com.filehandling.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.filehandling.entity.FileEntityy;
import com.filehandling.service.FileEntityyService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/files")
public class FileEntityyController {

    @Autowired
    private FileEntityyService fileService;

    @PostMapping("/upload")
    public ResponseEntity<FileEntityy> uploadFile(@RequestParam("file") MultipartFile file) {
        FileEntityy fileEntity = fileService.storeFile(file);
         // return ResponseEntity.ok(fileEntity);
        return new ResponseEntity<>(fileEntity,HttpStatus.CREATED);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<FileEntityy> downloadFile(@PathVariable Long id) {
        FileEntityy fileEntity = fileService.getFile(id);
        // return ResponseEntity.ok(fileEntity);
        return new ResponseEntity<>(fileEntity,HttpStatus.OK);
        
    }
}
