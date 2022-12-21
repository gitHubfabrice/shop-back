package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter 
@Setter
@NoArgsConstructor
@Entity
@Table(name = "shop_delivery_vendor")
public class DeliveryVendor {
	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;
	private String reference;
	private LocalDateTime deliveryAt;
	private double amount;
		
	@OneToMany(mappedBy = "pk.deliveryVendor", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<ArticleVendor> articleVendors = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Vendor vendor;

}