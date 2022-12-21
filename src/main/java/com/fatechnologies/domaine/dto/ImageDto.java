package com.fatechnologies.domaine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties
@EqualsAndHashCode(of= {"id"})
public class ImageDto {
	
	private Long id;	
	private String name;
	private String dossierName;	
	private Long articleId;	
	
	public void reset() {

		id = null;
		name = null;
		dossierName = null;
		articleId = null;

		
		
	}

}
