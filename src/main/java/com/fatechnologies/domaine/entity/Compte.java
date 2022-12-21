package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter 
@Entity
@Table(name="shop_compte")
public class Compte {
	
	@Id
	@SequenceGenerator(name = "gen_shop_compte", sequenceName = "seq_shop_compte", allocationSize = 1)
	@GeneratedValue(generator = "gen_shop_compte")
	private Long id;
	private String code;
	private Double solde;


}
