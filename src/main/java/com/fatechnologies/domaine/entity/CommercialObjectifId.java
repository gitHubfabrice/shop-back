package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class CommercialObjectifId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commercial_id", nullable = false)
	private Commercial commercial;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "objectif_id", nullable = false)
	private Objectif objectif;

}
