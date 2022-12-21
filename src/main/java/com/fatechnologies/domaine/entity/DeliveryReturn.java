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
@NoArgsConstructor
@Entity
@Table(name = "shop_delivery_return")
public class DeliveryReturn {
	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;
	private String code;
	private Date date;
	
		
	@OneToMany(mappedBy = "pk.deliveryReturn", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<ArticleReturn> articleReturns =new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Prospect client;

}