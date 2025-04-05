package com.filehandling.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.filehandling.entity.FileUpload;
import com.filehandling.repository.FileUploadRepository;

@Service
public class FileUploadService {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadService.class);
	
	@Autowired
    private FileUploadRepository uploadRepository;

		// File Upload
	    public FileUpload uploadFile(MultipartFile file) throws Exception {
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	
	            try {
	                if (fileName.contains("..")) {
	                    throw new Exception("The file name is invalid" + fileName);
	                }
	                FileUpload fileUpload = new FileUpload(file.getOriginalFilename(), file.getContentType(), file.getBytes());
	                logger.info("File Upload Successfully with Name ::{} ",fileName);
	                return uploadRepository.save(fileUpload);
	            } catch (Exception e) {
	                throw new Exception("File could not be save");
	            }
        }

    
        // File Download  
        public FileUpload downloadFile(String fileId) throws Exception {
        	logger.info("File Download Successfully with id ::",fileId);
        return uploadRepository.findById(fileId)
                .orElseThrow(() -> new Exception("A file with Id : "+ fileId + " could not be found"));
        }
    }

