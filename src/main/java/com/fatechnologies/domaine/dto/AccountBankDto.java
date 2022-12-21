package com.fatechnologies.domaine.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AccountBankDto {
	
	private UUID id;
	private String code;
	private Double solde;
	
	private Long clientId;

}
