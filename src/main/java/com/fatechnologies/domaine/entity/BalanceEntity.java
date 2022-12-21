package com.fatechnologies.domaine.entity;

import com.fatechnologies.security.domain.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "shop_balance")
public class BalanceEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private double amount;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToOne(mappedBy = "balance")
    private ProspectEntity client;


    public void deposit(double amount){
        this.amount += amount;
    }

    public void withdrawal(double amount){
        this.amount -= amount;
    }

}
