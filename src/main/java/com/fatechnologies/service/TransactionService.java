package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.TransactionDto;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
	 
	TransactionDto getById(UUID id);

	void balanceToAccountBank(TransactionDto dto);

	void transfer(TransactionDto dto);

	void withdrawal(TransactionDto dto);
	void delete(UUID id);
	List<TransactionDto> getAll();
}
