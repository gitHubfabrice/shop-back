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
@EqualsAndHashCode(of = { "id" })
public class VilleDto {

	private Long id;
	private String code;
	private String name;
	private String pays;
	private String codePays;
	
	public void reset() {

		id = null;
		code = null;
		name = null;
		pays = null;
		codePays = null;
		

	}

}
