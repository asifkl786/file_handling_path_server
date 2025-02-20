package com.filehandling.service;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	String fileUpload(String path, MultipartFile file);
	InputStream downloadFile(String path, String fileName) throws FileNotFoundException;

}
