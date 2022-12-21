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
@Entity
@NoArgsConstructor
@Table(name = "shop_order_vendor")
public class OrderVendor {
	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;
	private String code;
	private LocalDateTime date;
	private double amount;
	private String status;
	
	@OneToMany(mappedBy = "pk.order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ArticleFourCommande> articleOrders =new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Vendor vendor;

}
