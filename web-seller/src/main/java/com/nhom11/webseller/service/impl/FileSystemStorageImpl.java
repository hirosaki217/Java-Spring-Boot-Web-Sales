package com.nhom11.webseller.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nhom11.webseller.config.StorageProperties;
import com.nhom11.webseller.exception.StorageException;
import com.nhom11.webseller.exception.StorageFileNotFoundException;
import com.nhom11.webseller.service.StorageService;

@Service
public class FileSystemStorageImpl implements StorageService{
	private final Path rootLocation;
	@Override
	public String getStoredFilename(MultipartFile file, String id) {
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		return "p"+id+ "." + ext;
	}
	
	public FileSystemStorageImpl(StorageProperties properties) {
		this.rootLocation = Path.of(properties.getLocation());
	}
	
	@Override
	public void store(MultipartFile file, String storedFilename) {
		try {
			if(file.isEmpty())
				throw new StorageException("failed to store empty file");
			Path destinationFile = this.rootLocation.resolve(Paths.get(storedFilename))
					.normalize().toAbsolutePath();
			System.out.println(destinationFile.toString());
			if(!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath()))
				throw new StorageException("Cannot store file outside current directory");
//			try(InputStream inputStream = file.getInputStream()){
//				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
//			}
		} catch (Exception e) {
			throw new StorageException("Failed to store file ",e);
		}
	}
	
	@Override
	public Resource loadAsResoure(String filename) {
		try {
			Path path = load(filename);
			Resource resource = new UrlResource(path.toUri());
			if(resource.exists() || resource.isReadable()) {
				return resource;
			}
			throw new StorageFileNotFoundException("Could not read file: " + filename);
			
		} catch (Exception e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename);
			
		}
	}
	
	
    @Override
	public Stream<Path> loadAll() {
        try {
            //list all files in storageFolder
            //How to fix this ?
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation) && !path.toString().contains("._"))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to load stored files", e);
        }

    }
	
    @Override
	public boolean isImageFile(MultipartFile file) {
        
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[] {"png","jpg","jpeg", "bmp"})
                .contains(fileExtension.trim().toLowerCase());
    }
	@Override
	public byte[] readFileContent(String filename) {
		try {
			Path path = load(filename);
			Resource resource = new UrlResource(path.toUri());
			if(resource.exists() || resource.isReadable()) {
				byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
				return bytes;
			}
			throw new StorageFileNotFoundException("Could not read file: " + filename);
			
		} catch (Exception e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename);
			
		}
	}
	
	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}
	
	@Override
	public void delete(String storedFileName) throws IOException {
		Path destinationFile = rootLocation.resolve(Paths.get(storedFileName)).normalize().toAbsolutePath();
		Files.delete(destinationFile);
	}
	
	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
			System.out.println(rootLocation.toString());
		} catch (Exception e) {
			throw new StorageException("Could not initialize storage " , e);
		}
	}
}
