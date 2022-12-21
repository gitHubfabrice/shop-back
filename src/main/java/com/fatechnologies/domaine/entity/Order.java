package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "shop_order")
public class Order {

	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;
	private String code;
	private String status;
	private boolean valide;
	private Date date;
	private double amount;
	
	@OneToMany(mappedBy = "pk.order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ArticleOrder> articleOrders =new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	private Prospect client;


}
