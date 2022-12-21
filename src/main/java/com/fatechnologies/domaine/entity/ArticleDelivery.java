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
@Table(name = "shop_article_delivery_rl")
@AssociationOverrides({ @AssociationOverride(name = "pk.article", joinColumns = @JoinColumn(name = "article_id")),
		@AssociationOverride(name = "pk.delivery", joinColumns = @JoinColumn(name = "delivery_id")) })

public class ArticleDelivery implements Serializable{
	@Serial
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	ArticleLivraisonId pk = new ArticleLivraisonId();
	
	private int quantityArtLiv;

	private double priceArtDel;
	
	
	@Transient
	public ArticleEntity getArticle() {
		return getPk().getArticleEntity();
	}

	public void setArticle(ArticleEntity articleEntity) {
		getPk().setArticleEntity(articleEntity);
	}
	
	@Transient
	public Delivery getDelivery() {
		return getPk().getDelivery();
	}
	
	public void setDelivery(Delivery Delivery) {
		getPk().setDelivery(Delivery);
	}
	
}
