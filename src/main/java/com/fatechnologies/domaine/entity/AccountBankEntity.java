package com.fatechnologies.domaine.entity;

import com.fatechnologies.security.domain.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import javax.persistence.*;
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
	@ManyToOne(fetch = FetchType.EAGER)
	private UserEntity user;


	public void deposit(double amount){
		this.amount += amount;
	}

	public void withdrawal(double amount){
		this.amount -= amount;
	}


}
