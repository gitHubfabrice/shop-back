package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.TransactionDto;
import com.fatechnologies.domaine.dto.TypeTransaction;
import com.fatechnologies.domaine.entity.TransactionEntity;
import com.fatechnologies.domaine.mapper.TransactionMapper;
import com.fatechnologies.domaine.paypod.ChartOption;
import com.fatechnologies.domaine.paypod.FinanceCheckPoint;
import com.fatechnologies.repository.AccountBankRepository;
import com.fatechnologies.repository.BalanceRepository;
import com.fatechnologies.repository.TransactionRepository;
import com.fatechnologies.security.adapter.repository.jpa.UserJpa;
import com.fatechnologies.security.exception.BasicException;
import com.fatechnologies.security.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
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
		var dto = transactionMapper.modelToDto(operation);
		dto.setAmountTemp(operation.getAmount());
		return dto;
	}
	@Override
	public void balanceToAccountBank(TransactionDto dto) {

		var balance = balanceRepository.findOneBalanceByUserId(dto.getUserId()).orElseThrow(BasicException::new);

		//refund of the amount in case of modification
		balance.deposit(dto.getAmountTemp());

		//deposit the amount
		balance.withdrawal(dto.getAmount());
		var transaction  = transactionMapper.dtoToModel(dto);
		transaction.setCreatedAt(LocalDateTime.now());
		transaction.setNature(TypeTransaction.CREDIT);
		transaction.setReference(transaction.getReference() != null ? transaction.getReference() : String.valueOf(10000 + idGen()));
		transaction.setLabel("Transfer");
		balanceRepository.saveAndFlush(balance);
		transactionRepository.saveAndFlush(transaction);
	}

	@Override
	public void deposit(TransactionDto dto) {
		var transaction = transactionMapper.dtoToModel(dto);
		transaction.setReference(transaction.getReference() != null ? transaction.getReference() : String.valueOf(10000 + idGen()));
		transaction.setLabel("Transfer direct");
		transaction.setCreatedAt(LocalDateTime.now());
		transaction.setDirect(true);
		transaction.setCreatedAt(LocalDateTime.now());
		transaction.setNature(TypeTransaction.CREDIT);

		transactionRepository.saveAndFlush(transaction);
	}

	@Override
	public void withdrawal(TransactionDto dto) {
		var accountBank = accountBankRepository.findOneByReferenceIgnoreCase(Constants.COMPTE_PRINCIPAL).orElseThrow(BasicException::new);
		//
		accountBank.deposit(dto.getAmountTemp());
		//deposit the amount
		accountBank.withdrawal(dto.getAmount());

		var transaction = transactionMapper.dtoToModel(dto);
		transaction.setLabel(Constants.toUpperCase(transaction.getLabel()));
		transaction.setCreatedAt(LocalDateTime.now());
		transaction.setNature(TypeTransaction.DEBIT);
		transaction.setReference(transaction.getReference() != null ? transaction.getReference() : String.valueOf(10000 + idGen()));

		accountBankRepository.saveAndFlush(accountBank);
		transactionRepository.saveAndFlush(transaction);
	}

	@Override
	public void saveMoney(TransactionDto dto) {
		var accountBankPrincipal = accountBankRepository.findOneByReferenceIgnoreCase(Constants.COMPTE_PRINCIPAL).orElseThrow(BasicException::new);
		var accountBankSaveMoney = accountBankRepository.findOneByReferenceIgnoreCase(Constants.COMPTE_SAVE_MONEY).orElseThrow(BasicException::new);

		//deposit the amount
		accountBankPrincipal.deposit(dto.getAmountTemp());
		accountBankSaveMoney.withdrawal(dto.getAmountTemp());

		//deposit the amount
		accountBankPrincipal.withdrawal(dto.getAmount());
		accountBankSaveMoney.deposit(dto.getAmount());

		var transaction = transactionMapper.dtoToModel(dto);

		transaction.setCreatedAt(LocalDateTime.now());
		transaction.setNature(TypeTransaction.CREDIT);
		transaction.setStatus(true);
		transaction.setLabel("Epargne");
		transaction.setReference(transaction.getReference() != null ? transaction.getReference() : String.valueOf(10000 + idGen()));

		accountBankRepository.saveAndFlush(accountBankSaveMoney);
		accountBankRepository.saveAndFlush(accountBankPrincipal);
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
		dtos.sort(Comparator.comparing(TransactionDto::getCreatedAt).reversed());
		return dtos;
	}

	@Override
	public List<TransactionDto> getAllByUserInYear(UUID id) {
		var transactions = transactionRepository.findAllByUserIdAndLabelIgnoreCase(id, Constants.LABEL_TRANSACTION);
		var dtos = new ArrayList<TransactionDto>();
		for (var transaction : transactions) {
			var dto = transactionMapper.modelToDto(transaction);
			dto.setAmountTemp(transaction.getAmount());
			dtos.add(dto);
		}
		dtos.sort(Comparator.comparing(TransactionDto::getCreatedAt).reversed());
		return dtos;
	}

	@Override
	public FinanceCheckPoint getFinanceCheckPoint(UUID id) {
		var transactions = transactionRepository.findAllByUserIdAndLabelIgnoreCase(id, Constants.LABEL_TRANSACTION);
		var finance = new FinanceCheckPoint();
		var now = LocalDateTime.now();
		for (var transaction : transactions) {
			if (transaction.getCreatedAt().getMonth().equals(now.getMonth())){
				finance.moreMonth(transaction.getAmount());
			}
			if (transaction.getCreatedAt().getYear() == now.getYear()){
				finance.moreYear(transaction.getAmount());
			}
		}
		return finance;
	}

	@Override
	public List<TransactionDto> getAllByStatus(boolean status) {
		var transactions = transactionRepository.findAllByStatus(status);
		var dtos = new ArrayList<TransactionDto>();

		for (var transaction : transactions) {
			var dto = transactionMapper.modelToDto(transaction);
			dto.setAmountTemp(transaction.getAmount());
			dtos.add(dto);
		}
		dtos.sort(Comparator.comparing(TransactionDto::getCreatedAt).reversed());
		return dtos;
	}

	@Override
	public List<TransactionDto> getAllDebit() {
		var transactions = transactionRepository.findAllDebit();
		var dtos = new ArrayList<TransactionDto>();

		for (var transaction : transactions) {
			var dto = transactionMapper.modelToDto(transaction);
			dto.setAmountTemp(transaction.getAmount());
			dtos.add(dto);
		}
		dtos.sort(Comparator.comparing(TransactionDto::getCreatedAt).reversed());
		return dtos;
	}

	@Override
	public List<TransactionDto> getAllCredit(boolean direct) {
		var transactions = transactionRepository.findAllCredit(direct);
		var dtos = new ArrayList<TransactionDto>();

		for (var transaction : transactions) {
			var dto = transactionMapper.modelToDto(transaction);
			dto.setAmountTemp(transaction.getAmount());
			dtos.add(dto);
		}
		dtos.sort(Comparator.comparing(TransactionDto::getCreatedAt).reversed());
		return dtos;
	}

	@Override
	public List<TransactionDto> getAllByLabel(String label) {
		var transactions = transactionRepository.findAllByLabelIgnoreCase(label);
		var dtos = new ArrayList<TransactionDto>();

		for (var transaction : transactions) {
			var dto = transactionMapper.modelToDto(transaction);
			dto.setAmountTemp(transaction.getAmount());
			dtos.add(dto);
		}
		dtos.sort(Comparator.comparing(TransactionDto::getCreatedAt).reversed());
		return dtos;
	}

	@Override
	public void checkingTransaction(UUID id) {

		var transaction = transactionRepository.findById(id).orElseThrow(BasicException::new);
		var accountBank = accountBankRepository.findOneByReferenceIgnoreCase(Constants.COMPTE_PRINCIPAL).orElseThrow(BasicException::new);
			accountBank.deposit(transaction.getAmount());
			transaction.setStatus(true);
			accountBankRepository.saveAndFlush(accountBank);
			transactionRepository.saveAndFlush(transaction);
	}

	@Override
	public ChartOption getOptions() {
		var transactions = transactionRepository.findAll();
		var charOption = new ChartOption();
		for (TransactionEntity transaction : transactions) {
			if (transaction.getNature().equals(TypeTransaction.DEBIT))
				charOption.cumulus(transaction.getCreatedAt().getMonthValue(),0, transaction.getAmount());

			if (transaction.getNature().equals(TypeTransaction.CREDIT))
				charOption.cumulus(transaction.getCreatedAt().getMonthValue(),transaction.getAmount(), 0);
		}
		return charOption;
	}

	public int idGen(){
		var nbre = transactionRepository.nbre();
		if (nbre == 0)
			return 1;
		else return transactionRepository.max() + 1;
	}
}
