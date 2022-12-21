package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "shop_article_vendor_rl")
@AssociationOverrides({ @AssociationOverride(name = "pk.article", joinColumns = @JoinColumn(name = "article_id")),
		@AssociationOverride(name = "pk.deliveryVendor", joinColumns = @JoinColumn(name = "article_vendor_id")) })

public class ArticleVendor implements Serializable{
	@Serial
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	ArticleVendorId pk = new ArticleVendorId();
	
	private int qtyArtLiv;
	private Double priceArtLiv;
	
	@Transient
	public ArticleEntity getArticle() {
		return getPk().getArticleEntity();
	}

	public void setArticle(ArticleEntity articleEntity) {
		getPk().setArticleEntity(articleEntity);
	}
	
	@Transient
	public DeliveryVendor getDeliveryVendor() {
		return getPk().getDeliveryVendor();
	}
	
	public void setDeliveryVendor(DeliveryVendor deliveryVendor) {
		getPk().setDeliveryVendor(deliveryVendor);
	}
	
}
