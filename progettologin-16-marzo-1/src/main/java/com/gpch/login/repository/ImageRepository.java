package com.gpch.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gpch.login.model.Images;

@Repository
public interface ImageRepository extends JpaRepository<Images, Long> {

}