package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "shop_stock_value")
@Getter
@Setter
public class StockValue {
    @Id
    @GeneratedValue
    @SequenceGenerator(name = "gen_shop_stock_value", sequenceName = "seq_shop_stock_value", allocationSize = 1)
    private Long id;
    private Instant createdAt;
    private double amount;

    public StockValue(double amount){
        this.createdAt = Instant.now();
        this.amount = amount;
    }
}
