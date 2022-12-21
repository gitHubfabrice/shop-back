package com.fatechnologies.repository;

import com.fatechnologies.domaine.entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, UUID>{
	

}
