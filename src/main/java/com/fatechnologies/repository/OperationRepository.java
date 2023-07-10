package com.fatechnologies.repository;

import com.fatechnologies.domaine.dto.TypeOperation;
import com.fatechnologies.domaine.entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, UUID>{

	List<OperationEntity> findAllByType(TypeOperation type);

	List<OperationEntity> findAllByStatusAndDebtor(boolean status, boolean debtor);

	List<OperationEntity> findAllByUserIdAndStatus(UUID id, boolean status);
	List<OperationEntity> findAllByUserIdAndStatusAndDebtor(UUID id, boolean status, boolean debtor);

	@Query("SELECT sum(aop.quantity) FROM ArticleOperation aop  where aop.pk.article.id = :id and aop.type = :type")
	Integer sommeQuantity(@Param("id") Integer id, @Param("type") TypeOperation type);

	@Query(value="SELECT max(reference) FROM OperationEntity")
	int max();

	@Query(value="SELECT count(reference) FROM OperationEntity")
	int nbre();
}
