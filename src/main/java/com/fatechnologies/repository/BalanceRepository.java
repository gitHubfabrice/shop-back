package com.fatechnologies.repository;


import com.fatechnologies.domaine.dto.TypeTransaction;
import com.fatechnologies.domaine.entity.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BalanceRepository extends JpaRepository<BalanceEntity, UUID> {
    @Query("Select b from BalanceEntity b Where b.user.id = :id")
    Optional<BalanceEntity> findOneBalanceByUserId(@Param("id") UUID userId);
    @Query("Select sum(amount) From TransactionEntity  where nature = :nature")
    double getBalanceAt(@Param("nature") TypeTransaction nature);
}
