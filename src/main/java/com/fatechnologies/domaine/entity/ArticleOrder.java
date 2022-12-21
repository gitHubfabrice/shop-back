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
@Table(name = "shop_article_order_rl")
@AssociationOverrides({ @AssociationOverride(name = "pk.article", joinColumns = @JoinColumn(name = "article_id")),
		@AssociationOverride(name = "pk.order", joinColumns = @JoinColumn(name = "order_id")) })

public class ArticleOrder implements Serializable{
	@Serial
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	ArticleOrderId pk = new ArticleOrderId();
	
	private int quantityArtCom;
	
	private int quantityArtLiv;
	
	private Double priceArtCom;
	
	
	@Transient
	public Article getArticle() {
		return getPk().getArticle();
	}

	public void setArticle(Article article) {
		getPk().setArticle(article);
	}
	
	@Transient
	public Order getOrder() {
		return getPk().getOrder();
	}
	
	public void setOrder(Order order) {
		getPk().setOrder(order);
	}
	
}
