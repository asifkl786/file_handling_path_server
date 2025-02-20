package com.filehandling.serviceImple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.filehandling.service.FileDataService;
import com.filehandling.service.FileService;

@Service
public class FileServiceImple implements FileService{
	private static final Logger logger = LoggerFactory.getLogger(FileServiceImple.class);

	// This method upload file
	@Override
	public String fileUpload(String path, MultipartFile file) {
		
		// File Name 
		String orignalName = file.getOriginalFilename();
		
		// Random id generate
		String randomId = UUID.randomUUID().toString();
		String fileNameWithId = randomId.concat(orignalName.substring(orignalName.lastIndexOf(".")));
		
		// Fullpath
		String filePath = path+ File.separator+fileNameWithId;
		// create folder if not created
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		// file copy
		try {
			Files.copy(file.getInputStream(), Paths.get(filePath));
			logger.info("{} :: file successfully upload",file.getOriginalFilename());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orignalName;
	}

	
	// Download file method
	@Override
	public InputStream downloadFile(String path, String fileName) throws FileNotFoundException {
		String fullPath = path+File.separator+fileName;
	    InputStream	inputStream = new FileInputStream(fullPath);
	    logger.info("{} :: file successfully download",fileName);
		return inputStream;
	}

}
