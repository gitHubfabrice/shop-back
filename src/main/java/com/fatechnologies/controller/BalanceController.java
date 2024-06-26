package com.fatechnologies.controller;

import com.fatechnologies.domaine.dto.BalanceDto;
import com.fatechnologies.domaine.mapper.BalanceMapper;
import com.fatechnologies.repository.BalanceRepository;
import com.fatechnologies.security.exception.BasicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("shop/balance")
public class BalanceController {
	@Autowired
	private BalanceRepository balanceRepository;
	@Autowired
	private BalanceMapper balanceMapper;
	@GetMapping(value = "/get-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BalanceDto> getAById(@PathVariable("id") UUID id) {
		var balance = balanceRepository.findOneBalanceByUserId(id).orElseThrow(BasicException::new);
		return ResponseEntity.ok().body(balanceMapper.modelToDto(balance));
	}

	
}
