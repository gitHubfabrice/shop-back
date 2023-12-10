package com.fatechnologies.domaine.entity;

import com.fatechnologies.domaine.dto.TypeOperation;
import com.fatechnologies.security.domain.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "shop_operation")
public class OperationEntity {

  @Id
  @GeneratedValue
  @UuidGenerator
  private UUID id;
  private Integer reference;
  private LocalDate date;
  private LocalDateTime createdAt;
  private TypeOperation type;
  private double amount;
  @Column(name = "amount_benefice",columnDefinition = "default 0.0")
  private double amountBenefice;
  @Column(name = "status",columnDefinition = "boolean default false")
  private boolean status;
  @Column(name = "debtor",columnDefinition = "boolean default false")
  private boolean debtor;
  @ManyToOne(fetch = FetchType.LAZY)
  private ProspectEntity client;

  @ManyToOne(fetch = FetchType.EAGER)
  private UserEntity user;

  @OneToMany(mappedBy = "pk.operation", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private List<ArticleOperation> articles = new ArrayList<>();

}
