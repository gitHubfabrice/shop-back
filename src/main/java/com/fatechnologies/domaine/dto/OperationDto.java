package com.fatechnologies.domaine.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class OperationDto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	private UUID id;
	private Integer reference;
	private LocalDate date;
	private LocalDateTime createdAt;
	private TypeOperation type;
	private boolean status;
	private boolean debtor;
	private double amount;
	private double amountTemp;
	private double amountBenefice;
	private double amountBeneficeTemp;
	private UUID clientBalanceId;
	private UUID userId;
	private long clientId;
	private String clientLastname;
	private String clientFirstname;
	private String clientCityLabel;
	private List<ArticleDto> articles = new ArrayList<>();


}
