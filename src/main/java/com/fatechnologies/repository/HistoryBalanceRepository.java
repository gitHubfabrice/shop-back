package com.fatechnologies.repository;


import com.fatechnologies.domaine.entity.HistoryBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryBalanceRepository extends JpaRepository<HistoryBalance, Long> {

}
