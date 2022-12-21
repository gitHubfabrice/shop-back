package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class ArticleOperationId implements Serializable{
	@Serial
	private static final long serialVersionUID = 1L;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "article_id", nullable = false)
	private ArticleEntity article;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "operation_id", nullable = false)
	private OperationEntity operation;

}
