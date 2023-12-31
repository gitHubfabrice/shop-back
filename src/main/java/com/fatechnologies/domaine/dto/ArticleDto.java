package com.fatechnologies.domaine.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ArticleDto {
	private int id;
	private String reference;
	private String label;
	private ArticleStatus status;
	private int quantity;
	private int quantityOld;
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
	private int qtyOut;
	private int qtyIn;

	private final Set<FileDto> files;

	public ArticleDto() {
		files = new HashSet<>();
	}
}
