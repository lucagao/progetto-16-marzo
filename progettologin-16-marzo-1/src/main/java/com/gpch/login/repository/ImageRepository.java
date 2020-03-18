package com.gpch.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gpch.login.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}