package com.fatechnologies.repository;

import com.fatechnologies.domaine.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
	
	@Query("Select c from Compte c where c.id = :compteClientId")
	Compte findCompteById(@Param("compteClientId") Long compteClientId);
	

}
