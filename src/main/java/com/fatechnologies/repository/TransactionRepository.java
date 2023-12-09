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
    @Query("Select t From TransactionEntity t Where t.nature = com.fatechnologies.domaine.dto.TypeTransaction.DEBIT")
    List<TransactionEntity> findAllDebit();
    @Query("Select t From TransactionEntity t Where t.nature = com.fatechnologies.domaine.dto.TypeTransaction.CREDIT And t.direct = :direct")
    List<TransactionEntity> findAllCredit(@Param("direct") boolean direct);
    List<TransactionEntity> findAllByUserIdAndLabelIgnoreCase(UUID id, String label);
    List<TransactionEntity> findAllByLabelIgnoreCase(String label);


    @Query(value="SELECT max(reference) FROM TransactionEntity")
    int max();
    @Query(value="SELECT count(reference) FROM TransactionEntity")
    int nbre();
}
