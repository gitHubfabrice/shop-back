package com.fatechnologies.domaine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties
public class ProspectDto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private Long id;
	private String code;	
	private String name;
	private String surname;
	private Date createdAt;
	private String type;
	private String gender;
	private String adresse;
	private String email;
	private String contact;
	private String function;
	private Boolean client;
	private Long commercialId;
	private String status;
	private Date birthday;
	
	private Double solde;
	private Long compteId;
	
	private Long villeId;
	private String villeLabel;
	private String villePays;
	private String villeCodePays;

	private String denomination;
	private String index;

	private String surface;
	private String localisation;
	private String tail;

}
