package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter 
@NoArgsConstructor
@Entity
@Table(name="shop_category")
public class Category {
	
	@Id
	@SequenceGenerator(name = "gen_shop_category", sequenceName = "seq_shop_category", allocationSize = 1)
	@GeneratedValue(generator = "gen_shop_category")
	private int id;
	private String label;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<ArticleEntity> articleEntities = new HashSet<>();

}
