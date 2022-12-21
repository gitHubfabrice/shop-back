package com.fatechnologies.domaine.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class TransactionDto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	private UUID id;
	private double amount;
	private double amountTemp;
	private TypeTransaction nature;
	private LocalDateTime createdAt;
	private UUID balanceId;
	private UUID accountBankId;
	private boolean status;
	private boolean direct;

}
