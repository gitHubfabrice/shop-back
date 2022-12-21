package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.OperationDto;

import java.util.List;
import java.util.UUID;

public interface OperationService {
	OperationDto getById(UUID id);
	void inStock(OperationDto dto);
	void outStock(OperationDto dto);
	void delete(UUID id);
	List<OperationDto> getAll();
}
