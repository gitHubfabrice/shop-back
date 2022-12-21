package com.fatechnologies.repository;

import com.fatechnologies.domaine.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

	@Query("Select o from Order o where o.code = :code")
    Order findByCode(@Param("code") String code);
	
	@Query("Select o from Order o where o.status = :status")
	List<Order> findByStatus(@Param("status") String status);

}
