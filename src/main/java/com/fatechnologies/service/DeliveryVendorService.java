package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.DeliveryDto;

import java.util.List;
import java.util.UUID;

public interface DeliveryVendorService {
	
	DeliveryDto getById(UUID id);
	void create(DeliveryDto delivery);
	void update(DeliveryDto delivery);
	void delete(UUID id);
	List<DeliveryDto> getAll();

}
