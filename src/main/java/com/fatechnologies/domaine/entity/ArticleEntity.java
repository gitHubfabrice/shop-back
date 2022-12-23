package com.fatechnologies.domaine.entity;

import com.fatechnologies.security.domain.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "shop_article")
public class ArticleEntity {
	
	@Id
	@SequenceGenerator(name = "gen_shop_article", sequenceName = "seq_shop_article", allocationSize = 1)
	@GeneratedValue(generator = "gen_shop_article")
	private int id;
	private String reference;
	private String label;
	private int quantity;
	private String description;
	private boolean published;
	private LocalDateTime createdAt;
	private double price;
	@ManyToOne(fetch = FetchType.LAZY)
	private CategoryEntity category;

	@ManyToOne(fetch = FetchType.EAGER)
	private UserEntity user;
	public void more(int qty){
		this.quantity += qty;
	}

	public void less(int qty){
		this.quantity += qty;
	}

}
