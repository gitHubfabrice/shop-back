package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.OperationDto;

import java.util.List;

public interface OperationService {
	void inStock(OperationDto dto);
	void outStock(OperationDto dto);

	List<OperationDto> getAll();
}
