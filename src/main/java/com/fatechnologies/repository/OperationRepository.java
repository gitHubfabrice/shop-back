package com.fatechnologies.repository;

import com.fatechnologies.domaine.dto.TypeOperation;
import com.fatechnologies.domaine.entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, UUID>{
	List<OperationEntity> findAllByType(TypeOperation type);
	List<OperationEntity> findAllByStatus(boolean status);

}
