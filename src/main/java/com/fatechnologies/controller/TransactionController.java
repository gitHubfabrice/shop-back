package com.fatechnologies.controller;

import com.fatechnologies.domaine.dto.TransactionDto;
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

	@PostMapping(value = "/balance-to-account-bank", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void balanceToAccountBank(@RequestBody TransactionDto data) {
		transactionService.balanceToAccountBank(data);
	}
	
	@PutMapping(value = "/transfer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void transfer(@RequestBody TransactionDto data) {
		transactionService.transfer(data);
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
