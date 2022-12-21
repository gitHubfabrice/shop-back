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
public class CompteDto {
	
	private Long id;
	private String code;
	private Double solde;
	
	private Long clientId;

}
