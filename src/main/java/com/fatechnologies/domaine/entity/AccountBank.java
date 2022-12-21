package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter 
@Entity
@Table(name="shop_account-bank")
public class AccountBank {

	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;
	private String reference;
	private double amount;

	public void deposit(double amount){
		this.amount += amount;
	}

	public void withdrawal(double amount){
		this.amount -= amount;
	}


}
