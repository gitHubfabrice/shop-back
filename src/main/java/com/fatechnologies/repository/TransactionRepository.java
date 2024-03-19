package com.fatechnologies.repository;

import com.fatechnologies.domaine.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID>{

    List<TransactionEntity> findAllByStatus(boolean status);
    @Query("Select t From TransactionEntity t Where t.nature = com.fatechnologies.domaine.dto.TypeTransaction.DEBIT " +
            "And Year(t.createdAt) = Year(CURRENT_DATE)")
    List<TransactionEntity> findAllDebit();
    @Query("Select t From TransactionEntity t Where t.nature = com.fatechnologies.domaine.dto.TypeTransaction.CREDIT And t.direct = :direct")
    List<TransactionEntity> findAllCredit(@Param("direct") boolean direct);
    @Query("SELECT t FROM TransactionEntity t WHERE YEAR(t.createdAt) = :year and t.user.id = :id and LOWER(t.label) = LOWER(:label)" )
    List<TransactionEntity> findAllByUserIdAndLabelIgnoreCase(@Param("id") UUID id, @Param("label") String label, @Param("year") int year);
    List<TransactionEntity> findAllByLabelIgnoreCase(String label);
    @Query("SELECT t FROM TransactionEntity t WHERE YEAR(t.createdAt) = :year")
    List<TransactionEntity> findAllByYear(@Param("year") int year);

    @Query("SELECT TO_CHAR(created_at, 'YYYY-MM') AS mois, COUNT(*) AS nombre_de_transaction, SUM(amount) AS montant_total " +
            "FROM TransactionEntity WHERE YEAR(created_at) = :year " +
            "GROUP BY TO_CHAR(created_at, 'YYYY-MM') ORDER BY mois")
    List<TransactionEntity> getOption(@Param("year") int year);


    @Query(value="SELECT max(reference) FROM TransactionEntity")
    int max();
    @Query(value="SELECT count(reference) FROM TransactionEntity")
    int nbre();
}
