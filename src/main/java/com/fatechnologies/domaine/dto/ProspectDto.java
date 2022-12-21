package com.fatechnologies.domaine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties
public class ProspectDto extends  Person implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private Long id;
	private String reference;
	private LocalDateTime createdAt;
	private boolean isClient;
	private Long commercialId;
	private Date birthday;
	private UUID balanceId;
	private Long cityId;
	private String cityLabel;
	private double pay;

}
