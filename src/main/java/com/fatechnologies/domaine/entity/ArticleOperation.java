package com.fatechnologies.domaine.entity;

import com.fatechnologies.domaine.dto.TypeOperation;
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
@Table(name = "shop_article_operation_rl")
@AssociationOverrides({ @AssociationOverride(name = "pk.article", joinColumns = @JoinColumn(name = "article_id")),
        @AssociationOverride(name = "pk.operation", joinColumns = @JoinColumn(name = "operation_id")) })
public class ArticleOperation implements Serializable{
  @Serial
  private static final long serialVersionUID = 1L;

  @EmbeddedId
  ArticleOperationId pk = new ArticleOperationId();
  private int quantity;
  private double price;
  private TypeOperation type;

  @Transient
  public ArticleEntity getArticle() {
    return getPk().getArticle();
  }

  public void setArticle(ArticleEntity article) {
    getPk().setArticle(article);
  }

  @Transient
  public OperationEntity getOperation() {
    return getPk().getOperation();
  }

  public void setOperation(OperationEntity operation) {
    getPk().setOperation(operation);
  }

}
