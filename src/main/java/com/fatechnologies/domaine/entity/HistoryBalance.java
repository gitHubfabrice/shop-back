package com.fatechnologies.domaine.entity;

import com.fatechnologies.domaine.dto.TypeTransaction;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
@Entity
@Table(name = "shop_history_balance")
@Getter
@Setter
public class HistoryBalance {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime createdAt;
    private double balance;
    private double amount;
    private TypeTransaction typeTransaction;

    public HistoryBalance(double balance,
                          double amount,
                          TypeTransaction typeTransaction) {
        this.createdAt = LocalDateTime.now();
        this.amount = amount;
        this.balance = balance;
        this.typeTransaction = typeTransaction;
    }

    public HistoryBalance() {

    }
}
