package com.fatechnologies.repository;

import com.fatechnologies.domaine.dto.TypeOperation;
import com.fatechnologies.domaine.entity.OperationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, UUID>{
	@Query("SELECT op FROM OperationEntity op  WHERE  op.type = :type AND EXTRACT(YEAR FROM created_at) = EXTRACT(YEAR FROM CURRENT_DATE)")
	List<OperationEntity> findAllByTypeOrderByCreatedAtDesc(@Param("type") TypeOperation type);

	Page<OperationEntity> findAllByTypeOrderByCreatedAtDesc(TypeOperation type, Pageable pageable);

	List<OperationEntity> findAllByStatusAndDebtor(boolean status, boolean debtor);

	List<OperationEntity> findAllByUserIdAndStatus(UUID id, boolean status);
	List<OperationEntity> findAllByUserIdAndStatusAndDebtor(UUID id, boolean status, boolean debtor);

	@Query("SELECT sum(aop.quantity) FROM ArticleOperation aop  where aop.pk.article.id = :id and aop.type = :type")
	Integer sommeQuantity(@Param("id") Integer id, @Param("type") TypeOperation type);


	@Query(value = """
            SELECT
                 COALESCE(((SELECT SUM(amount_benefice) FROM shop_operation
                 WHERE EXTRACT(MONTH FROM created_at) = EXTRACT(MONTH FROM CURRENT_DATE)
                 AND EXTRACT(YEAR FROM created_at) = EXTRACT(YEAR FROM CURRENT_DATE)
                 AND type = 1 AND status = true) - (SELECT SUM(amount) FROM shop_transaction sp
                 WHERE EXTRACT(MONTH FROM sp.created_at) = EXTRACT(MONTH FROM CURRENT_DATE)
                 AND EXTRACT(YEAR FROM sp.created_at) = EXTRACT(YEAR FROM CURRENT_DATE)
                 AND nature = 1)), 0) AS solde_mois,
                \s
                 COALESCE(((SELECT SUM(amount_benefice) FROM shop_operation
                 WHERE EXTRACT(YEAR FROM created_at) = EXTRACT(YEAR FROM CURRENT_DATE)
                 AND type = 1 AND status = true) - (SELECT SUM(amount) FROM shop_transaction sp
                 WHERE EXTRACT(YEAR FROM sp.created_at) = EXTRACT(YEAR FROM CURRENT_DATE)
                 AND nature = 1) + 1000000), 0) AS solde_annee
             
           """, nativeQuery = true)
	Map<Object, Object> getBenefice();

	@Query(value="SELECT max(reference) FROM OperationEntity")
	int max();

	@Query(value="SELECT count(reference) FROM OperationEntity")
	int nbre();
}
