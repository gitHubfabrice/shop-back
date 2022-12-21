package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "shop_commercial_objectif_rl")
@AssociationOverrides({
		@AssociationOverride(name = "pk.commercial", joinColumns = @JoinColumn(name = "commercial_id")),
		@AssociationOverride(name = "pk.objectif", joinColumns = @JoinColumn(name = "objectif_id")) })
public class CommercialObjectif implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	CommercialObjectifId pk = new CommercialObjectifId();
	
	@Column(name="date_debut")
	private Date dateDebut;
	
	@Column(name="date_fin")
	private Date dateFin;
	
	@Transient
	public Commercial getCommercial() {
		return getPk().getCommercial();
	}

	public void setCommercial(Commercial commercial) {
		getPk().setCommercial(commercial);
	}

	@Transient
	public Objectif getObjectif() {
		return getPk().getObjectif();
	}

	public void setObjectif(Objectif objectif) {
		getPk().setObjectif(objectif);
	}

}
