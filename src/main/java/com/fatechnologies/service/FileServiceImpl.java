package com.fatechnologies.service;

import com.fatechnologies.config.EledProperties;
import com.fatechnologies.domaine.dto.FileDto;
import com.fatechnologies.domaine.mapper.FileMapper;
import com.fatechnologies.repository.FileRepository;
import com.fatechnologies.security.exception.BasicException;
import com.fatechnologies.security.exception.Exception;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;
@Service
public class FileServiceImpl implements FileService {

	private final EledProperties properties;
	private final FileRepository fileRepository;
	private final FileMapper fileMapper;

	public FileServiceImpl(EledProperties properties, FileRepository fileRepository) {
		this.properties = properties;
		this.fileRepository = fileRepository;
		fileMapper = FileMapper.INSTANCE;
	}

	@Override
	public FileDto getById(UUID id) {
		var file = fileRepository.findById(id).orElseThrow(BasicException::new);
		return fileMapper.modelToDto(file);
	}

	@Override
	public FileDto saveImage(FileDto file, String articleReference) {
		if (file.getFile() != null && !file.getFile().equals("") && file.getFilename() != null && !file.getFilename().equals("")){
			String base64Image = Objects.requireNonNull(file.getFile()).split(",")[1];
			var data = Base64.decodeBase64(base64Image);

			Path uploadPath = Paths.get(properties.getDirFile(), "articles", articleReference);
			file.setUrl(uploadPath.toString());
			file.setFilename(UUID.randomUUID() + "_"  + file.getFilename());
			file.setType(Objects.requireNonNull(file.getFile()).split(",")[0]);

			try (InputStream inputStream = new ByteArrayInputStream(data)) {
				if (!Files.exists(uploadPath)) {
					Files.createDirectories(uploadPath);
				}
				Path filePath = uploadPath.resolve(file.getFilename());
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

			} catch (IOException ioe) {
				Logger.getLogger(ioe.toString());
				throw new Exception("Could not store the file. Error: " + file.getFilename());
			}
			return file;
		}

		return file;
	}

	@Override
	public String loadImage(String filename, String type, String url) {
		if (filename != null && !filename.equals("") && type != null && url != null) {
			try {
				Path uploadPath = Paths.get(url);
				Path file = uploadPath.resolve(filename).normalize();
				Resource resource = new UrlResource(file.toUri());
				if (resource.exists() || resource.isReadable()) {
					return type + "," + convertFileToBase64(resource.getInputStream());
				} else {
					throw new Exception("Impossible de lire le fichier!");
				}
			} catch (MalformedURLException e) {
				throw new Exception("Erreur: " + e.getMessage());
			} catch (IOException e) {
				throw new Exception(e.getMessage());
			}
		}

		return null;
	}

	@Override
	public void delete(UUID id, String url, String filename) throws IOException {
		try {
			Files.deleteIfExists(Paths.get(url, filename));
		} catch (NoSuchFileException e) {
			Logger.getLogger("No such file/directory exists");
		} catch (DirectoryNotEmptyException e) {
			Logger.getLogger("Directory is not empty.");
		} catch (IOException e) {
			Logger.getLogger("Invalid permissions.");
		}
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