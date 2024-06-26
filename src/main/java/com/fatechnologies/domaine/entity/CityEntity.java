package com.fatechnologies.domaine.entity;

import com.fatechnologies.security.domain.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "shop_city")
public class CityEntity {
	
	@Id
	@SequenceGenerator(name = "gen_shop_city", sequenceName = "seq_shop_city",  allocationSize = 1)
	@GeneratedValue(generator = "gen_shop_city")
	private Long id;
	private String label;
	private String country;
	private String codeCountry;
	@ManyToOne(fetch = FetchType.EAGER)
	private UserEntity user;


}
