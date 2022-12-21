package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="shop_commission") 
public class Commission {
	@Id
	@SequenceGenerator(name = "gen_commission", sequenceName = "seq_shop_commission", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "gen_commission")
	private Long id;
	private String code;
	private Double taux;
	
	@OneToMany(mappedBy = "pk.commission",cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<CommercialCommission> commercialCommissions =new ArrayList<>();
	
	public Commission(Long commissionId) {
		this.id = commissionId;
	}

}
