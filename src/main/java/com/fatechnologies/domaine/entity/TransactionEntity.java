package com.fatechnologies.domaine.entity;

import com.fatechnologies.domaine.dto.TypeTransaction;
import com.fatechnologies.security.domain.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import javax.persistence.*;
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
	private Integer reference;
	private String label;
	private double amount;
	private TypeTransaction nature;
	private LocalDateTime createdAt;
	private boolean status;
	private boolean direct;
	@ManyToOne(fetch = FetchType.EAGER)
	private UserEntity user;
}
