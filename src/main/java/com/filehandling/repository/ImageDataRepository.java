package com.filehandling.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filehandling.entity.ImageData;

public interface ImageDataRepository extends JpaRepository<ImageData,Long>{

	Optional<ImageData> findByName(String fileName);
}
