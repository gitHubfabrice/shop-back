package com.fatechnologies.repository;

import com.fatechnologies.domaine.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ASSAGOU FABRICE 15/04/2023
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
