package com.fatechnologies.domaine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter 
@Setter
@NoArgsConstructor
@JsonIgnoreProperties
@EqualsAndHashCode(of= {"id"})
public class VendorDto implements Serializable {

	/**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;
	private Long id;
	private String code;
	private String lastname;
	private String firstname;
	private String gender;
	private String adresse;
	private String email;
	private String contact;
	private String function;
	
	private Long villeId;
	private String villeLabel;
	private String villePays;
	private String villeCodePays;


}
