package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.OperationDto;

import java.util.List;

public interface OperationService {
	 
	OperationDto getById(Long id);
	OperationDto create(OperationDto dto);
	OperationDto update(OperationDto dto);
	void delete(Long id);
	List<OperationDto> getAll();
}
