package com.fatechnologies.controller;

import com.fatechnologies.domaine.dto.AccountBankDto;
import com.fatechnologies.service.AccountBankService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RestController
@NoArgsConstructor
@RequestMapping("shop/account-bank")

public class AccountBankController {
	private Logger log = LoggerFactory.getLogger(AccountBankController.class);

	@Autowired
	private AccountBankService accountBankService;

	@GetMapping(value = "/get-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountBankDto> findById(@PathVariable("id") UUID id) {
		var accountBankDto = accountBankService.getById(id);
		return ResponseEntity.ok().body(accountBankDto);
	}


	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody AccountBankDto compte) {
		accountBankService.save(compte);
	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody AccountBankDto compte) {
		 accountBankService.save(compte);
	}

	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AccountBankDto>> getAll() {
		var accountBanks = accountBankService.getAll();
		return ResponseEntity.ok().body(accountBanks);
	}

	@DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable("id") UUID id) {
		accountBankService.delete(id);
	}

}
