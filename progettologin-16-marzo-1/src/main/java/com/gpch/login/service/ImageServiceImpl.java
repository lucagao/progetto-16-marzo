package com.gpch.login.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.gpch.login.model.Image;
import com.gpch.login.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository immagineRepos;

	@Override
	public Image salvaFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				return null;
			}

			Image dbFile = new Image(fileName, file.getContentType(), file.getBytes());

			return immagineRepos.save(dbFile);
		} catch (IOException ex) {
			return null;
		}
	}

	@Override
	public Image recuperaFile(Long fileId) {
		return immagineRepos.findById(fileId).orElseThrow(() -> null); // se il risultato della ricerca è null, restituisce null, altrimenti restituisce il risultato
	}
}