package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "shop_prospect")
public class Prospect {

	@Id
	@SequenceGenerator(name = "gen_prospect", sequenceName = "seq_shop_prospect", initialValue = 2, allocationSize = 1)
	@GeneratedValue(generator = "gen_prospect")
	private Long id;
	private String code;
	private String lastname;
	private String surname;
	private String gender;
	private String adresse;
	private Date createdAt;
	private String email;
	private String contact;
	private Date birthday;
	private String function;
	
	private Boolean client;
	private String status;
	
	private String denomination;
	
	private String type;

	private String surface;
	private String localisation;
	private String tail;
	
	private String index;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Ville ville;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Compte compte;

}
