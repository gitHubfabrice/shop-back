package com.fatechnologies.domaine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties
@EqualsAndHashCode(of= {"id"})
public class CommissionDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String code;
	private Double taux;
	private Date dateDebut;
	private Date dateFin;


	public void reset() {
		id = null;
		code = null;
		taux = null;
		dateDebut = null;
		dateFin  = null;			

	}


}
