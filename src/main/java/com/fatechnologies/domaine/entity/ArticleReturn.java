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
@Table(name = "shop_return_article_rl")
@AssociationOverrides({ @AssociationOverride(name = "pk.article", joinColumns = @JoinColumn(name = "article_id")),
		@AssociationOverride(name = "pk.deliverReturn", joinColumns = @JoinColumn(name = "delivery_return_id")) })

public class ArticleReturn implements Serializable{
	@Serial
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	ArticleReturnId pk = new ArticleReturnId();
	
	private int qtyReturn;
	
	@Transient
	public Article getArticle() {
		return getPk().getArticle();
	}

	public void setArticle(Article article) {
		getPk().setArticle(article);
	}
	
	@Transient
	public DeliveryReturn getDeliveryReturn() {
		return getPk().getDeliveryReturn();
	}
	
	public void setDeliveryReturn(DeliveryReturn deliveryReturn) {
		getPk().setDeliveryReturn(deliveryReturn);
	}
	
}
