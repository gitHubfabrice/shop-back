package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * @author ASSAGOU FABRICE 18/04/2023
 */
@Entity
@Table(name = "shop_balance_history")
@Getter
@Setter
public class BalanceHistory {
  @Id
  private LocalDate createdAt;
  private double amount;


}
