package com.fatechnologies.domaine.entity;

import com.fatechnologies.domaine.dto.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "shop_prospect")
public class ProspectEntity{

	@Id
	@SequenceGenerator(name = "gen_shop_prospect", sequenceName = "seq_shop_prospect", allocationSize = 1)
	@GeneratedValue(generator = "gen_shop_prospect")
	private Long id;
	private String reference;
	private LocalDateTime createdAt;
	private boolean client;
	@ManyToOne(fetch = FetchType.LAZY)
	private City city;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "balance_id", referencedColumnName = "id")
	private BalanceEntity balance;
	@Embedded
	private Person person;

}
