package com.fatechnologies.domaine.entity;

import com.fatechnologies.domaine.dto.TypeOperation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "shop_operation")
public class OperationEntity {

	@Id
	@GeneratedValue
	@UuidGenerator
	private UUID id;
	private String reference;
	private LocalDateTime createdAt;
	private TypeOperation type;
	private double amount;
	@ManyToOne(fetch = FetchType.LAZY)
	private ProspectEntity client;

	@OneToMany(mappedBy = "pk.operation", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<ArticleOperation> articles =new ArrayList<>();

}
