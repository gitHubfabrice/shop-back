package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Setter
@Getter 
@Entity
@Table(name="shop_account_bank")
public class AccountBankEntity {

	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;
	private String reference;
	private String label;
	private double amount;


	public void deposit(double amount){
		this.amount += amount;
	}

	public void withdrawal(double amount){
		this.amount -= amount;
	}


}
