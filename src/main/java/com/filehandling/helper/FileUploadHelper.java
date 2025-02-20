package com.filehandling.helper;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

	public final String UPLOAD_DIR="C:\\Users\\Asif Khan\\Documents\\workspace-spring-tool-suite-4-4.25.0.RELEASE\\FileHandlingProject\\src\\main\\resources\\static\\images";
	
	public boolean uploadFile(MultipartFile file) {
		boolean filee =false;
		
		try {
			Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR+ file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			filee=true;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return filee;
	}
}
