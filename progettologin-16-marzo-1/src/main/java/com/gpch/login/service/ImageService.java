package com.gpch.login.service;

import org.springframework.web.multipart.MultipartFile;

import com.gpch.login.model.Image;

public interface ImageService {

	public Image salvaFile(MultipartFile file); // upload

	public Image recuperaFile(Long fileId); // download

}