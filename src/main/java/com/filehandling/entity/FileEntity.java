package com.filehandling.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "File_Entity")
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

	
}

