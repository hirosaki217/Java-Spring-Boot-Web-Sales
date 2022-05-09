package com.nhom11.webseller.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	void init();

	void delete(String storedFileName) throws IOException;

	Path load(String filename);

	Resource loadAsResoure(String filename);

	void store(MultipartFile file, String storedFilename);

	String getStoredFilename(MultipartFile file, String id);

	byte[] readFileContent(String filename);

	boolean isImageFile(MultipartFile file);

	Stream<Path> loadAll();

}
