package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Images;

@Repository
public interface ImageRepository extends JpaRepository<Images, Long> {

	Optional<Images> findById(String fileId);

}