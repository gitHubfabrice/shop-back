package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.TransactionDto;
import com.fatechnologies.domaine.dto.TypeTransaction;
import com.fatechnologies.domaine.mapper.TransactionMapper;
import com.fatechnologies.repository.AccountBankRepository;
import com.fatechnologies.repository.BalanceRepository;
import com.fatechnologies.repository.TransactionRepository;
import com.fatechnologies.security.adapter.repository.jpa.UserJpa;
import com.fatechnologies.security.exception.BasicException;
import com.fatechnologies.security.exception.Exception;
import com.fatechnologies.security.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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
	@Autowired
	private UserJpa userJpa;
	@Override
	public TransactionDto getById(UUID id) {
		var operation = transactionRepository.findById(id).orElseThrow(BasicException::new);
		return transactionMapper.modelToDto(operation);
	}
	@Override
	public void balanceToAccountBank(TransactionDto dto) {

		var balance = userJpa.findOneBalanceByUserId(dto.getUserId()).orElseThrow(BasicException::new);

		//refund of the amount in case of modification
		balance.deposit(dto.getAmountTemp());

		//deposit the amount
		balance.withdrawal(dto.getAmount());
		var transaction  = transactionMapper.dtoToModel(dto);
		transaction.setCreatedAt(LocalDateTime.now());
		transaction.setNature(TypeTransaction.CREDIT);
		transaction.setReference(transaction.getReference() != null ? transaction.getReference() : String.valueOf(10000 + idGen()));

		balanceRepository.saveAndFlush(balance);
		transactionRepository.saveAndFlush(transaction);
	}

	@Override
	public void transfer(TransactionDto dto) {
		var transaction = transactionMapper.dtoToModel(dto);
		transaction.setReference(transaction.getReference() != null ? transaction.getReference() : String.valueOf(10000 + idGen()));

		transaction.setDirect(true);
		transaction.setCreatedAt(LocalDateTime.now());
		transaction.setNature(TypeTransaction.CREDIT);
		transaction.setStatus(true);

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
		transaction.setCreatedAt(LocalDateTime.now());
		transaction.setNature(TypeTransaction.DEBIT);
		transaction.setReference(transaction.getReference() != null ? transaction.getReference() : String.valueOf(10000 + idGen()));

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
		var dtos = new ArrayList<TransactionDto>();

		for (var transaction : transactions) {
		var dto = transactionMapper.modelToDto(transaction);
			dto.setAmountTemp(transaction.getAmount());
			dtos.add(dto);
		}
		return dtos;

	}

	@Override
	public List<TransactionDto> getAllByStatus() {
		var transactions = transactionRepository.findAllByStatus(false);
		var dtos = new ArrayList<TransactionDto>();

		for (var transaction : transactions) {
			var dto = transactionMapper.modelToDto(transaction);
			dto.setAmountTemp(transaction.getAmount());
			dtos.add(dto);
		}
		return dtos;
	}
	@Override
	public void checkingTransaction(UUID id) {

		var transaction = transactionRepository.findById(id).orElseThrow(BasicException::new);
		var accountBank = accountBankRepository.findOneByReference(Constants.COMPTE_PRINCIPAL).orElseThrow(BasicException::new);
		var balance = userJpa.findOneBalanceByUserId(transaction.getUser().getId()).orElseThrow(BasicException::new);
		if (balance.getAmount() >= transaction.getAmount()) {
			accountBank.deposit(transaction.getAmount());
			balance.withdrawal(transaction.getAmount());
			transaction.setStatus(true);

			accountBankRepository.saveAndFlush(accountBank);
			balanceRepository.saveAndFlush(balance);
			transactionRepository.saveAndFlush(transaction);

		} else throw new Exception("vérifier votre solde");

	}

	public int idGen(){
		var nbre = transactionRepository.nbre();
		if (nbre == 0)
			return 1;
		else return transactionRepository.max() + 1;
	}

}
