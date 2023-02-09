package com.fatechnologies.domaine.entity;

import com.fatechnologies.security.domain.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
	@Column(columnDefinition = "integer default 0")
	private int quantityOld;
	private String description;
	private boolean published;
	private LocalDateTime createdAt;
	private double price;
	@ManyToOne(fetch = FetchType.LAZY)
	private CategoryEntity category;
	@OneToMany(mappedBy = "article", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE} )
	private final Set<FileEntity> files = new HashSet<>();


	@ManyToOne(fetch = FetchType.EAGER)
	private UserEntity user;

	public void more(int qty){
		this.quantity += qty;
	}

	public void less(int qty){
		this.quantity -= qty;
	}

}
