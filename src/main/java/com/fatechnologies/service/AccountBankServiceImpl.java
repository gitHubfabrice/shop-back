package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.AccountBankDto;
import com.fatechnologies.domaine.dto.TypeTransaction;
import com.fatechnologies.domaine.entity.AccountBankEntity;
import com.fatechnologies.domaine.entity.HistoryBalance;
import com.fatechnologies.domaine.mapper.AccountBankMapper;
import com.fatechnologies.repository.AccountBankRepository;
import com.fatechnologies.repository.HistoryBalanceRepository;
import com.fatechnologies.repository.OperationRepository;
import com.fatechnologies.security.exception.BasicException;
import com.fatechnologies.security.exception.Exception;
import com.fatechnologies.security.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Service
@Transactional
public class AccountBankServiceImpl implements AccountBankService {

	private final AccountBankRepository accountBankRepository;

	private final AccountBankMapper accountBankMapper;
	private final OperationRepository operationRepository;
	private final HistoryBalanceRepository historyBalanceRepository;

	public AccountBankServiceImpl(AccountBankRepository accountBankRepository,
								  OperationRepository operationRepository,
								  HistoryBalanceRepository historyBalanceRepository) {
		this.accountBankRepository = accountBankRepository;
		this.accountBankMapper = AccountBankMapper.INSTANCE;
		this.operationRepository = operationRepository;
		this.historyBalanceRepository = historyBalanceRepository;
	}

	@Override
	public AccountBankDto getById(UUID id) {
		var accountBank = accountBankRepository.findById(id).orElseThrow(BasicException::new);
		return accountBankMapper.modelToDto(accountBank);
	}

	@Override
	public AccountBankDto getByReference(String reference) {
		var accountBank = accountBankRepository.findOneByReferenceIgnoreCase(reference).orElseThrow(BasicException::new);
		return accountBankMapper.modelToDto(accountBank);
	}

	@Override
	public void debitBankAccount(UUID operationId) {
		var operation = operationRepository.findById(operationId);
		operation.ifPresent(op -> {
			if (op.isDebtor()){
				return;
			}
			var accountBank = accountBankRepository
							.findOneByReferenceIgnoreCase(Constants.ACCOUNT_PRINCIPAL)
							.orElseThrow(() -> new Exception("Veuillez contacter l'administrateur"));
			accountBank.withdrawal(op.getAmount());
			op.setStatus(true);
			op.setDebtor(true);
			operationRepository.saveAndFlush(op);
			accountBankRepository.saveAndFlush(accountBank);
			historyBalanceRepository.save(new HistoryBalance(accountBank.getAmount(), op.getAmount(), TypeTransaction.DEBIT));
		});
	}

	@Override
	public void save(AccountBankDto accountBankDto) {

		var accountBank = accountBankMapper.dtoToModel(accountBankDto);
		accountBank.setLabel(Constants.toUpperCase(accountBank.getLabel()));
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

		dtos.sort(Comparator.comparing(AccountBankDto::getLabel));
		return dtos;

	}
}
