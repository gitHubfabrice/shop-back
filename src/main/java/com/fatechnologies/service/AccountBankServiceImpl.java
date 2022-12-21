package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.AccountBankDto;
import com.fatechnologies.domaine.entity.AccountBankEntity;
import com.fatechnologies.domaine.mapper.AccountBankMapper;
import com.fatechnologies.repository.AccountBankRepository;
import com.fatechnologies.security.exception.BasicException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Service
@Transactional
public class AccountBankServiceImpl implements AccountBankService {

	@Autowired
	private AccountBankRepository accountBankRepository;

	@Autowired
	private AccountBankMapper accountBankMapper;

	@Override
	public AccountBankDto getById(UUID id) {
		var accountBank = accountBankRepository.findById(id).orElseThrow(BasicException::new);
		return accountBankMapper.modelToDto(accountBank);
	}

	@Override
	public void save(AccountBankDto accountBankDto) {

		var accountBank = accountBankMapper.dtoToModel(accountBankDto);
		accountBankRepository.saveAndFlush(accountBank);
	}


	@Override
	public void delete(UUID id) {
		accountBankRepository.deleteById(id);

	}

	@Override
	public List<AccountBankDto> getAll() {
		List<AccountBankEntity> accountBankEntities = accountBankRepository.findAll();
		List<AccountBankDto> dtos = new ArrayList<>();

		for (AccountBankEntity accountBankEntity : accountBankEntities) {
			var dto = accountBankMapper.modelToDto(accountBankEntity);
			dtos.add(dto);
		}
		return dtos;

	}
}
