package com.fatechnologies.domaine.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FileDto {

	private UUID id;
	private int articleId;
	private String type;
	private String filename;
	private String url;
	private String file;
	private boolean available;

}
