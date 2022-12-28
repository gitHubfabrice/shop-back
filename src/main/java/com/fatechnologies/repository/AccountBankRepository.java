package com.fatechnologies.repository;

import com.fatechnologies.domaine.entity.AccountBankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountBankRepository extends JpaRepository<AccountBankEntity, UUID> {
    Optional<AccountBankEntity> findOneByReferenceIgnoreCase(String reference);
}
