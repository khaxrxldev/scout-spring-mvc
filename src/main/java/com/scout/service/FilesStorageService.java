package com.scout.service;

import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
	
	public void init();

	public void insertFile(MultipartFile file);
	
	public byte[] retrieveFile(String fileName);
}
