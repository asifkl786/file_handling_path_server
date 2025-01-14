package com.filehandling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filehandling.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
