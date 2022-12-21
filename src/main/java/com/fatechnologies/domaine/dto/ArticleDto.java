package com.fatechnologies.domaine.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleDto {
	private int id;
	private String reference;
	private String label;
	private int quantity;
	private int quantityArtDel;
	private int quantityTemp;
	private double priceArtDel;
	private int quantityArtOrd;
	private int commissionId;
	private String commissionTaux;
	private double priceArtOrd;
	private String description;
	private boolean published;
	private LocalDateTime createdAt;
	private double price;
	private int categoryId;
	private String categoryLabel;
	public void more(int qty){
			this.quantity += qty;
	}

	public void less(int qty){
		this.quantity += qty;
	}


}
