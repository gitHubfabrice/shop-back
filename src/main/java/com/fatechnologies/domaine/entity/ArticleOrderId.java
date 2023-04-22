package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author ASSAGOU FABRICE 15/04/2023
 */
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class ArticleOrderId implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "article_id", nullable = false)
  private ArticleEntity article;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false)
  private OrderEntity order;
}
