package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.OrderDto;

import java.util.List;
import java.util.UUID;

public interface OrderVendorService {
	
	OrderDto getById(UUID id);
	OrderDto create(OrderDto commande);
	OrderDto update(OrderDto commande);
	void delete(UUID id);
	List<OrderDto> getAll();
}
