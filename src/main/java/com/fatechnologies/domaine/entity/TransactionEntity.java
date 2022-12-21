package com.fatechnologies.domaine.entity;

import com.fatechnologies.domaine.dto.TypeTransaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "shop_transaction")
public class TransactionEntity {

	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;
	private double amount;
	private TypeTransaction nature;
	private LocalDateTime createdAt;
	private boolean status;
	private boolean direct;
}
