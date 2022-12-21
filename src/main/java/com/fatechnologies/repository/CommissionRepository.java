package com.fatechnologies.repository;

import com.fatechnologies.domaine.entity.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Long>{
	

}
