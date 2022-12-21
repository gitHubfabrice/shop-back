package com.fatechnologies.repository;

import com.fatechnologies.domaine.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityRepository extends JpaRepository<City, Long>{
}
