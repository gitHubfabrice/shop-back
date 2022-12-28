package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.AccountBankDto;

import java.util.List;
import java.util.UUID;

public interface AccountBankService {
	 
	AccountBankDto getById(UUID id);
	AccountBankDto getByReference(String reference);
	void save(AccountBankDto compte);
	void delete(UUID id);
	List<AccountBankDto> getAll();



}
