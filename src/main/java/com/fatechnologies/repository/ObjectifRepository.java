package com.fatechnologies.repository;

import com.fatechnologies.domaine.entity.Objectif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ObjectifRepository extends JpaRepository<Objectif, Long>{
	

}
