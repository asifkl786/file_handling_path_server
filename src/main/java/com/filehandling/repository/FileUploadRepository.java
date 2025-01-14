package com.filehandling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filehandling.entity.FileUpload;

public interface FileUploadRepository extends JpaRepository<FileUpload,String> {

}
