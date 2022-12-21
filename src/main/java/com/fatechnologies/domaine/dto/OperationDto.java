package com.fatechnologies.domaine.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class OperationDto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	private Long id;
	private double amount;
	private double amountMemory;
	private String memoireNature;
	private String nature;
	private Date createdAt;
	private Long clientId;
	private Long compteClientId;
	private String clientLastname;
	private String clientContact;
	private String clientVilleLabel;
	private String clientVillePays;


}
