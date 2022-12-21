package com.fatechnologies.service;

import java.io.IOException;

public interface FileService {


	String          saveImage(String file, String fileName);
	String          loadImage(String image, String url);

	void delete(String url, String filename) throws IOException;
}