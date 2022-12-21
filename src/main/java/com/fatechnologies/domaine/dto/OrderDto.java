package com.fatechnologies.domaine.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter 
@Setter
public class OrderDto implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	private UUID id;
	private int count;
	private String code;
	private Date date;
	private Double amount;
	private String status;
	private boolean checked;
	private Long clientId;
	private String clientCode;
	private String clientLastname;
	private String clientVilleLabel;
	private String clientEmail;
	private String clientContact;
	
	
	private Long vendorId;
	private String vendorCode;
	private String vendorLastname;
	private String vendorContact;
	private String vendorVilleLabel;
	
	
	private Long commercialId;
	private String commercialLastname;
	private String commercialLogin;
	private String commercialEmail;
	private String commercialContact;
	private List<ArticleDto> articles= new ArrayList<>();
	private String message;


}
