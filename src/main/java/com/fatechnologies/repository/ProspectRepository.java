package com.fatechnologies.repository;

import com.fatechnologies.domaine.entity.ProspectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProspectRepository extends JpaRepository<ProspectEntity, Long>{
	
	@Query("Select p from Prospect p where p.contact = :contact")
    ProspectEntity findByContact(@Param("contact") String contact);
	
	@Query(value="SELECT max(code) FROM Prospect")
	int max();
	
	@Query(value="SELECT count(code) FROM Prospect")
	int nbre();

}
