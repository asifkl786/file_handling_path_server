package com.filehandling.response;

import lombok.Data;

@Data
public class FileUploadResponse {
   
	private String fileName;
    private String downloadURL;
    private String fileType;
    private long fileSize;
    
	public FileUploadResponse(String fileName, String downloadURL, String fileType, long fileSize) {
		super();
		this.fileName = fileName;
		this.downloadURL = downloadURL;
		this.fileType = fileType;
		this.fileSize = fileSize;
	}
  
}
