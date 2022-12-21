package com.fatechnologies.service;

import com.fatechnologies.config.EledProperties;
import com.fatechnologies.security.exception.Exception;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

public class FileServiceImpl implements FileService {

	private final EledProperties properties;

	public FileServiceImpl(EledProperties properties) {
		this.properties = properties;
	}

	@Override
	public String saveImage(String file, String filename) {
		if (file != null && !file.equals("") && filename != null && !filename.equals("")){
			String base64Image = Objects.requireNonNull(file).split(",")[1];
			var data = Base64.decodeBase64(base64Image);
			String finalFilename;

			Path uploadPath = Paths.get(properties.getDirFile(), "photo", "identity");
			finalFilename = UUID.randomUUID()+ "_"  + filename;

			try (InputStream inputStream = new ByteArrayInputStream(data)) {
				if (!Files.exists(uploadPath)) {
					Files.createDirectories(uploadPath);
				}
				Path filePath = uploadPath.resolve(finalFilename);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

			} catch (IOException ioe) {
				Logger.getLogger(ioe.toString());
				throw new Exception("Could not store the file. Error: " + finalFilename);
			}
			return finalFilename;
		}

		return null;
	}

	@Override
	public String loadImage(String image, String url) {
		return null;
	}

	@Override
	public void delete(String url, String filename) throws IOException {

	}

	public static String convertFileToBase64(InputStream input) {
		byte[] data = null;
		try {
			Logger.getLogger("file size (bytes)=" + input.available());
			data = new byte[input.available()];
			input.read(data);
			input.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		//Base64 encoded by byte arrays with a string of Base64 encoded
		return new String(Objects.requireNonNull(Base64.encodeBase64(data)));
	}
}