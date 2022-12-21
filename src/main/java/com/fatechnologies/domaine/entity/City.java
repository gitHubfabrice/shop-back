package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "shop_city")
public class City {
	
	@Id
	@SequenceGenerator(name = "gen_shop_city", sequenceName = "seq_shop_city",  allocationSize = 1)
	@GeneratedValue(generator = "gen_shop_city")
	private Long id;
	private String code;
	private String label;
	private String pays;
	private String codePays;

}
