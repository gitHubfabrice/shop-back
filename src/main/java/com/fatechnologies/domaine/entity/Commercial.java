package com.fatechnologies.domaine.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "shop_commercial")
public class Commercial {
	@Id
	@SequenceGenerator(name = "gen_commercial", sequenceName = "seq_shop_commercial", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator="gen_commercial")
	private Long id;
	private String code;
	private String lastname;
	private String firstname;
	private String login;
	private String gender;
	private Date birthday;
	private String adresse;
	private String email;
	private String contact;
	private String status;
	
	@OneToMany(mappedBy = "pk.commercial", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CommercialObjectif> commercialObjectifs =new ArrayList<>();
	
	@OneToMany(mappedBy = "pk.commercial", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CommercialCommission> commercialCommissions =new ArrayList<>();

}
