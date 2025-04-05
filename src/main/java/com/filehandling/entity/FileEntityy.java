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
    
}
