package com.fatechnologies.repository;

import com.fatechnologies.domaine.entity.DeliveryVendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface DeliveryVendorRepository extends JpaRepository<DeliveryVendor, UUID>{
	

}
