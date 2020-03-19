package com.gpch.login.service;

import org.springframework.web.multipart.MultipartFile;

import com.gpch.login.model.Images;

public interface ImageService {

	public Images salvaFile(MultipartFile file); // upload

	public Images recuperaFile(Long fileId); // download

}