package com.fatechnologies.domaine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties
@EqualsAndHashCode(of= {"id"})
public class CommercialDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nom;
	private String prenoms;
	private String login;
	private String sexe;
	private Date dateNaissance;
	private String adresse;
	private String email;
	private String contact;
	private String status;
	
	private List<ObjectifDto> objectifs = new ArrayList<ObjectifDto>();
	private List<CommissionDto> commissions = new ArrayList<CommissionDto>();

	public void reset() {
		id = null;
		nom = null;
		prenoms = null;
		login = null;
		sexe = null;
		dateNaissance = null;
		adresse = null;
		email = null;
		contact = null;
		status = null;
		objectifs.clear();
		commissions.clear();

	}


}
