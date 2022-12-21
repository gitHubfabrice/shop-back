package com.fatechnologies.domaine.dto;

import com.fatechnologies.domaine.entity.BalanceEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BalanceDto {
    private UUID id;
    private double amount;

    public BalanceDto(){}
    public BalanceDto(BalanceEntity balance){
        this.amount = balance.getAmount();
        this.id = balance.getId();
    }

    public void mountMore(double amount){
        this.amount += amount;
    }

    public void amountLess(double amount){
        this.amount -= amount;
    }
}
