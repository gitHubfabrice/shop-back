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
@Table(name = "shop_objectif")
@NoArgsConstructor
public class Objectif {
	@Id
	@SequenceGenerator(name = "gen_objectif", sequenceName = "seq_shop_objectif", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "gen_objectif")
	private Long id;
	private String libelle;
	
	@OneToMany(mappedBy = "pk.objectif",cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<CommercialObjectif> commercialObjectifs =new ArrayList<>();
	
	public Objectif(Long objectifId) {
		this.id = objectifId;
	}

}
