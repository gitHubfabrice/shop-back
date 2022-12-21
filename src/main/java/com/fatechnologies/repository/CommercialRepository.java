package com.fatechnologies.repository;

import com.fatechnologies.domaine.entity.Commercial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommercialRepository extends JpaRepository<Commercial, Long>{
	
    @Query("Select c from Commercial c where c.login = :login")
	List<Commercial> findByLogin(@Param("login") String login);
    
    @Query(value="SELECT max(code) FROM Commercial")
	int max();
	
	@Query(value="SELECT count(code) FROM Commercial")
	int nbre();

}
