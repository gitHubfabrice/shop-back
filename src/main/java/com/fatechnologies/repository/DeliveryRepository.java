package com.fatechnologies.repository;

import com.fatechnologies.domaine.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, UUID>{

}
