package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.TransactionDto;
import com.fatechnologies.domaine.mapper.TransactionMapper;
import com.fatechnologies.repository.AccountBankRepository;
import com.fatechnologies.repository.BalanceRepository;
import com.fatechnologies.repository.TransactionRepository;
import com.fatechnologies.security.exception.BasicException;
import com.fatechnologies.security.utils.Constants;
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
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private AccountBankRepository accountBankRepository;
	@Autowired
	private BalanceRepository balanceRepository;
	@Autowired
	private TransactionMapper transactionMapper;
	@Override
	public TransactionDto getById(UUID id) {
		var operation = transactionRepository.findById(id).orElseThrow(BasicException::new);
		return transactionMapper.modelToDto(operation);
	}
	@Override
	public void balanceToAccountBank(TransactionDto dto) {

		var accountBank = accountBankRepository.findOneByReference(Constants.COMPTE_PRINCIPAL).orElseThrow(BasicException::new);
		var balance = balanceRepository.findById(dto.getBalanceId()).orElseThrow(BasicException::new);

		//refund of the amount in case of modification
		balance.deposit(dto.getAmountTemp());
		accountBank.withdrawal(dto.getAmountTemp());

		//deposit the amount
		balance.withdrawal(dto.getAmount());
		accountBank.deposit(dto.getAmount());
		var transaction = transactionMapper.dtoToModel(dto);

		accountBankRepository.saveAndFlush(accountBank);
		balanceRepository.saveAndFlush(balance);
		transactionRepository.saveAndFlush(transaction);
	}

	@Override
	public void transfer(TransactionDto dto) {
		var accountBank = accountBankRepository.findOneByReference(Constants.COMPTE_PRINCIPAL).orElseThrow(BasicException::new);

		//refund of the amount in case of modification
		accountBank.withdrawal(dto.getAmountTemp());

		//deposit the amount
		accountBank.deposit(dto.getAmount());
		var transaction = transactionMapper.dtoToModel(dto);
		transaction.setDirect(true);

		accountBankRepository.saveAndFlush(accountBank);
		transactionRepository.saveAndFlush(transaction);
	}

	@Override
	public void withdrawal(TransactionDto dto) {
		var accountBank = accountBankRepository.findOneByReference(Constants.COMPTE_PRINCIPAL).orElseThrow(BasicException::new);

		//refund of the amount in case of modification
		accountBank.deposit(dto.getAmountTemp());

		//deposit the amount
		accountBank.withdrawal(dto.getAmount());
		var transaction = transactionMapper.dtoToModel(dto);

		accountBankRepository.saveAndFlush(accountBank);
		transactionRepository.saveAndFlush(transaction);
	}

	@Override
	public void delete(UUID id) {
		transactionRepository.deleteById(id);
	}

	@Override
	public List<TransactionDto> getAll() {
		var transactions = transactionRepository.findAll();
		List<TransactionDto> dtos = new ArrayList<>();

		for (var transaction : transactions) {
		var dto = transactionMapper.modelToDto(transaction);
			dto.setAmountTemp(transaction.getAmount());
			dtos.add(dto);
		}
		return dtos;

	}

}
