package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "shop_ville")
public class Ville {
	
	@Id
	@SequenceGenerator(name = "gen_shop_ville", sequenceName = "seq_shop_ville",  allocationSize = 1)
	@GeneratedValue(generator = "gen_shop_ville")
	private Long id;
	private String code;
	private String label;
	private String pays;
	private String codePays;

}
