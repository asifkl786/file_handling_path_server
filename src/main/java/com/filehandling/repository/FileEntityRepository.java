package com.filehandling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filehandling.entity.FileEntity;

@Repository
public interface FileEntityRepository extends JpaRepository<FileEntity, Long> {

}
