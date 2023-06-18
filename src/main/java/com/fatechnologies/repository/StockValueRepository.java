package com.fatechnologies.repository;


import com.fatechnologies.domaine.entity.StockValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockValueRepository extends JpaRepository<StockValue, Long> {
}
