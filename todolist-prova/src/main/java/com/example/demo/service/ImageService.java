package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Images;

public interface ImageService {

	public Images salvaFile(MultipartFile file); // upload

	public Images recuperaFile(String fileId); // download

}