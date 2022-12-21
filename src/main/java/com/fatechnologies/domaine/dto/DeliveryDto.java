package com.fatechnologies.domaine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonIgnoreProperties
@NoArgsConstructor
@EqualsAndHashCode(of= {"id"})
public class DeliveryDto {

	private UUID id;
	private String code;
	private Date date;
	private Double amount;
	private UUID orderId;
	private String orderCode;
	private Long vendorId;
	private String vendorCode;
	private String vendorContact;
	private String vendorLastname;
	private String clientCode;
	private String clientContact;
	private String clientLastname;
	private String clientVilleLabel;
	
	private String message;
	private List<ArticleDto> articles = new ArrayList<>();

}
