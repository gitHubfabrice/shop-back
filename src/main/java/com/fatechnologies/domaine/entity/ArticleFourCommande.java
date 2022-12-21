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
@Table(name = "shop_article_vend_order_rl")
@AssociationOverrides({ @AssociationOverride(name = "pk.article", joinColumns = @JoinColumn(name = "article_id")),
		@AssociationOverride(name = "pk.order", joinColumns = @JoinColumn(name = "order_vendor_id")) })

public class ArticleFourCommande implements Serializable{
	@Serial
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	ArticleVendorOrderId pk = new ArticleVendorOrderId();
	
	private int qtyArtOrd;
	
	private Double priceArtOrd;
	@Transient
	public ArticleEntity getArticle() {
		return getPk().getArticleEntity();
	}

	public void setArticle(ArticleEntity articleEntity) {
		getPk().setArticleEntity(articleEntity);
	}
	
	@Transient
	public OrderVendor getOrder() {
		return getPk().getOrder();
	}
	
	public void setOrder(OrderVendor order) {
		getPk().setOrder(order);
	}
	
}
