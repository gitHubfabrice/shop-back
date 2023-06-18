package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.FileDto;

import java.io.IOException;
import java.util.UUID;

public interface FileService {

	FileDto getById(UUID id);
	FileDto  saveImage(FileDto file, String articleReference);
	String   loadImage(String filename,String type, String url);
	void delete(UUID id, String url, String filename) throws IOException;
}