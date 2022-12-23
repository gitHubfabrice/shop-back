package com.fatechnologies.repository;

import com.fatechnologies.domaine.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID>{
    List<TransactionEntity> findAllByStatus(boolean status);

    @Query(value="SELECT max(reference) FROM TransactionEntity")
    int max();

    @Query(value="SELECT count(reference) FROM TransactionEntity")
    int nbre();
}
