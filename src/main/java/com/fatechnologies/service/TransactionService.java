package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.TransactionDto;
import com.fatechnologies.domaine.paypod.ChartOption;
import com.fatechnologies.domaine.paypod.FinanceCheckPoint;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
	 
	TransactionDto getById(UUID id);

	void balanceToAccountBank(TransactionDto dto);

	void transfer(TransactionDto dto);

	void withdrawal(TransactionDto dto);
	void saveMoney(TransactionDto dto);
	void delete(UUID id);
	List<TransactionDto> getAll();
	List<TransactionDto> getAllByUserInYear(UUID id);
	FinanceCheckPoint getFinanceCheckPoint(UUID id);
	List<TransactionDto> getAllByStatus(boolean status);
	void checkingTransaction(UUID id);

	ChartOption getOptions();
}
