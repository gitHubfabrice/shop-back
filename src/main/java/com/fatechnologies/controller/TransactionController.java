package com.fatechnologies.controller;

import com.fatechnologies.domaine.dto.TransactionDto;
import com.fatechnologies.domaine.paypod.ChartOption;
import com.fatechnologies.domaine.paypod.FinanceCheckPoint;
import com.fatechnologies.service.TransactionService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("shop/transaction")
@Getter
@Setter

public class TransactionController {
	private Logger log = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransactionService transactionService;
	@GetMapping(value = "/get-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TransactionDto> getById(@PathVariable("id") UUID id) {
		var  dtos = transactionService.getById(id);
		return ResponseEntity.ok().body(dtos);
	}


	@PutMapping(value = "/balance-to-account-bank", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void balanceToAccountBank(@RequestBody TransactionDto data) {
		transactionService.balanceToAccountBank(data);
	}
	
	@PutMapping(value = "/deposit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void transfer(@RequestBody TransactionDto data) {
		transactionService.deposit(data);
	}

	@PutMapping(value = "/withdraw", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void withdraw(@RequestBody TransactionDto data) {
		transactionService.withdrawal(data);
	}

	@PutMapping(value = "/save-money", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveMoney(@RequestBody TransactionDto data) {
		transactionService.saveMoney(data);
	}

	@PutMapping(value = "/benefit-to-bank", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void benefitToBank(@RequestBody TransactionDto data) {
		transactionService.save(data);
	}

	@PutMapping(value = "/checking/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void checking(@PathVariable("id") UUID id) {
		transactionService.checkingTransaction(id);
	}

	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TransactionDto>> getAll() {
		var  dtos = transactionService.getAll();
		return ResponseEntity.ok().body(dtos);
	}

	@GetMapping(value = "/get-all-debit", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TransactionDto>> getAllDebit() {
		var  dtos = transactionService.getAllDebit();
		return ResponseEntity.ok().body(dtos);
	}

	@GetMapping(value = "/get-all-credit/{direct}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TransactionDto>> getAllCredit(@PathVariable("direct") Boolean direct) {
		var  dtos = transactionService.getAllCredit(direct);
		return ResponseEntity.ok().body(dtos);
	}

	@GetMapping(value = "/get-all-by-label/{label}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TransactionDto>> getAllByLabel(@PathVariable("label") String label) {
		var  dtos = transactionService.getAllByLabel(label);
		return ResponseEntity.ok().body(dtos);
	}


	@GetMapping(value = "/get-chart-option", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ChartOption> getChartOption() {
		var  dto = transactionService.getOptions();
		return ResponseEntity.ok().body(dto);
	}

	@GetMapping(value = "/get-finance-check-point/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FinanceCheckPoint> getFinanceCheckPoint(@PathVariable("id") UUID id) {
		var  financeCheckPoint = transactionService.getFinanceCheckPoint(id);
		return ResponseEntity.ok().body(financeCheckPoint);
	}

	@GetMapping(value = "/get-by-status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TransactionDto>> getAllCheck(@PathVariable("status") boolean status) {
		var  dtos = transactionService.getAllByStatus(status);
		return ResponseEntity.ok().body(dtos);
	}

	@DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable("id") UUID id) {
		transactionService.delete(id);
	}
	
}
