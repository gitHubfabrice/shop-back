package com.fatechnologies.domaine.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shop_vendor")
public class Vendor {

	@Id
	@SequenceGenerator(name = "gen_vendor", sequenceName = "seq_shop_vendor", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "gen_vendor")
	private Long id;
	private String lastname;
	private String firstname;
	private String code;
	private String email;
	private String adresse;
	private String contact;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Ville ville;

}
