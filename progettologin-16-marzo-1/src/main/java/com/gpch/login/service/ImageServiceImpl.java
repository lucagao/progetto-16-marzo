package com.gpch.login.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gpch.login.model.Images;
import com.gpch.login.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository immagineRepos;

	@Override
	public Images salvaFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				return null;
			}

			Images dbFile = new Images(fileName, file.getContentType(), file.getBytes());

			return immagineRepos.save(dbFile);
		} catch (IOException ex) {
			return null;
		}
	}

	@Override
	public Images recuperaFile(Long fileId) {
		return immagineRepos.findById(fileId).orElseThrow(() -> null); 
	}
}