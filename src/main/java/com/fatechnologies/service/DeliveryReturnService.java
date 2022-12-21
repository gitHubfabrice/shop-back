package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.DeliveryDto;

import java.util.List;
import java.util.UUID;

public interface DeliveryReturnService {

	DeliveryDto getById(UUID id);
	void create(DeliveryDto dto);
	void update(DeliveryDto dto);
	void delete(UUID id);
	List<DeliveryDto> getAll();

}
