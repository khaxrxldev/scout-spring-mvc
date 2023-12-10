package com.scout.service;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author khaxr
 * @source https://www.bezkoder.com/spring-boot-file-upload/ 
 * @source https://stackoverflow.com/a/54571593
 */
@Service
public class FilesStorageServiceImpl implements FilesStorageService {
	private final Path root = Paths.get("upload");

	@Override
	public void init() {
		try {
			Files.createDirectories(root);
		} catch (IOException e) {
			throw new RuntimeException("Cannot initialize folder for upload.");
		}
	}

	@Override
	public void insertFile(MultipartFile multipartFile) {
		try {
			Files.copy(multipartFile.getInputStream(), this.root.resolve(multipartFile.getOriginalFilename()));
		} catch (Exception exception) {
			if (exception instanceof FileAlreadyExistsException) {
				throw new RuntimeException("File already exists.");
			}
			throw new RuntimeException(exception.getMessage());
		}
	}

	@Override
	public byte[] retrieveFile(String fileName) {
		try {
			byte[] fileByte = Files.readAllBytes(root.resolve(fileName));
			Path path = root.resolve(fileName);
			Resource resource = new UrlResource(path.toUri());

			if (resource.exists() || resource.isReadable()) {
				return fileByte;
			} else {
				throw new RuntimeException("Cannot read file.");
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}