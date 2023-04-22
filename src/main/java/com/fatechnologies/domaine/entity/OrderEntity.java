package com.fatechnologies.domaine.entity;

import com.fatechnologies.security.domain.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ASSAGOU FABRICE 15/04/2023
 */
@Entity
@Getter
@Setter
@Table(name = "shop_order")
public class OrderEntity {
  @Id
  @GeneratedValue
  @SequenceGenerator(name = "gen_shop_order", sequenceName = "seq_shop_order", allocationSize = 1)
  private Long id;
  private Integer reference;
  private boolean status;
  private double amount;
  private LocalDate date;
  @ManyToOne(fetch = FetchType.LAZY)
  private ProspectEntity client;
  @ManyToOne(fetch = FetchType.EAGER)
  private UserEntity user;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime validatedAt;
  @OneToMany(mappedBy = "pk.order", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private List<ArticleOrder> articles = new ArrayList<>();
}
