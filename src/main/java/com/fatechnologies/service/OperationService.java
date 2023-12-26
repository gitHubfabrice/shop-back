package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.OperationDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface OperationService {
	OperationDto getById(UUID id);
	void inStock(OperationDto dto);
	void outStock(OperationDto dto);
	void delete(UUID id);
	List<OperationDto> getAllInStockHistory();
	List<OperationDto> getAllOutStockHistory();
	Page<OperationDto> getAllOutStockHistory(int pageNumber, int pageSize);
}
