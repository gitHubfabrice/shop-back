package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;



@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "shop_image")
public class Image implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "gen_image", sequenceName = "seq_shop_image", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "gen_image")
	private Long id;	
	private String name;
	private String dossierName;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ArticleEntity articleEntity;
}
