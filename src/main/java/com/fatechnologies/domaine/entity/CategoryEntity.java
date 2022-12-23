package com.fatechnologies.domaine.entity;

import com.fatechnologies.security.domain.entity.UserEntity;
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
public class CategoryEntity {
	@Id
	@SequenceGenerator(name = "gen_shop_category", sequenceName = "seq_shop_category", allocationSize = 1)
	@GeneratedValue(generator = "gen_shop_category")
	private int id;
	private String label;
	@ManyToOne(fetch = FetchType.EAGER)
	private UserEntity user;
	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private Set<ArticleEntity> articles = new HashSet<>();

}
