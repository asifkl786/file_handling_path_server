package com.filehandling.entity;

import java.util.Arrays;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "files")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Lob
    private byte[] data; // To store file content (optional for database storage)

    @Column(nullable = false)
    private String filePath; // To store file path (optional for disk storage)

	public FileEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileEntity(Long id, String name, String type, byte[] data, String filePath) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.data = data;
		this.filePath = filePath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "FileEntity [id=" + id + ", name=" + name + ", type=" + type + ", data=" + Arrays.toString(data)
				+ ", filePath=" + filePath + "]";
	}
    
    
}

