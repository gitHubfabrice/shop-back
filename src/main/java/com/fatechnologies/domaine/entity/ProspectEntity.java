package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "shop_prospect")
public class ProspectEntity {

	@Id
	@SequenceGenerator(name = "gen_shop_prospect", sequenceName = "seq_shop_prospect", allocationSize = 1)
	@GeneratedValue(generator = "gen_shop_prospect")
	private Long id;
	private String reference;
	private String lastname;
	private String firstname;
	private String gender;
	private String address;
	private LocalDateTime createdAt;
	private String email;
	private String contact;
	private Date birthday;
	private String function;
	private boolean client;
	@ManyToOne(fetch = FetchType.LAZY)
	private City city;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "balance_id", referencedColumnName = "id")
	private BalanceEntity balance;

}
