package com.filehandling.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FileEntityy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType;
    private String filePath;
    
	public FileEntityy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileEntityy(Long id, String fileName, String fileType, String filePath) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileType = fileType;
		this.filePath = filePath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "FileEntityy [id=" + id + ", fileName=" + fileName + ", fileType=" + fileType + ", filePath=" + filePath
				+ "]";
	}
    
    
}
