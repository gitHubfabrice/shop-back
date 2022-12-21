package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.OrderDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {

	OrderDto getById(UUID id);

	OrderDto getByReference(String reference);

	List<OrderDto> getByStatus(String status);

	void create(OrderDto dto);

	void update(OrderDto dto);

	void delete(UUID id);

	List<OrderDto> getAll();
}