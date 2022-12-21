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
@Table(name = "shop_commercial_commission_rl")
@AssociationOverrides({
		@AssociationOverride(name = "pk.commercial", joinColumns = @JoinColumn(name = "commercial_id")),
		@AssociationOverride(name = "pk.commission", joinColumns = @JoinColumn(name = "commission_id")) })
public class CommercialCommission implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	CommercialCommissionId pk = new CommercialCommissionId();
	
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
	public Commission getCommission() {
		return getPk().getCommission();
	}

	public void setommission(Commission commission) {
		getPk().setCommission(commission);
	}

}
