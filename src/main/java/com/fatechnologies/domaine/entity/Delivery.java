package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter 
@Setter
@NoArgsConstructor
@Entity
@Table(name = "shop_delivery")
public class Delivery {
	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;
	private String code;
	private LocalDateTime deliveryAt;
	private double amount;
		
	@OneToMany(mappedBy = "pk.delivery", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<ArticleDelivery> articleDeliveries =new ArrayList<>();

}