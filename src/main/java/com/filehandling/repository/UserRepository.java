package com.filehandling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filehandling.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
