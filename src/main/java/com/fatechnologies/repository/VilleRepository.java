package com.fatechnologies.repository;

import com.fatechnologies.domaine.entity.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VilleRepository extends JpaRepository<Ville, Long>{


}
