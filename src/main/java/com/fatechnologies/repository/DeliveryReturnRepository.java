package com.fatechnologies.repository;

import com.fatechnologies.domaine.entity.DeliveryReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface DeliveryReturnRepository extends JpaRepository<DeliveryReturn, UUID>{

}
