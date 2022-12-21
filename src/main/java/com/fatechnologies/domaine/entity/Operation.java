package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "shop_operation")
public class Operation {

	@Id
	@SequenceGenerator(name = "gen_operation", sequenceName = "seq_shop_operation", allocationSize = 1)
	@GeneratedValue(generator = "gen_operation")
	private Long id;
	private Double amount;
	private String nature;
	private Date createdAt;

	@ManyToOne(fetch = FetchType.LAZY)
	private Prospect client;

	public Operation(Long operationId) {
		this.id = operationId;
	}

}
