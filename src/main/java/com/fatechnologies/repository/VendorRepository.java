package com.fatechnologies.repository;

import com.fatechnologies.domaine.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long>{

	@Query("Select v from Vendor v where v.contact = :contact")
    Vendor findByContact(@Param("contact") String contact);



}
