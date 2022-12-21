package com.fatechnologies.controller;

import com.fatechnologies.domaine.dto.OperationDto;
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

@RestController
@RequestMapping("shop/operation")
@Getter
@Setter

public class OperationController {
	private Logger log = LoggerFactory.getLogger(OperationController.class);

	@Autowired
	private TransactionService transactionService;

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OperationDto> create(@RequestBody OperationDto data) {

		OperationDto dto = transactionService.create(data);

		return ResponseEntity.ok().body(dto);
	}
	
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OperationDto> update(@RequestBody OperationDto data) {

		OperationDto dto = transactionService.update(data);

		return ResponseEntity.ok().body(dto);
	}
	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OperationDto>> getAllVille() {

		List<OperationDto> dtos = transactionService.getAll();

		return ResponseEntity.ok().body(dtos);
	}
	

	

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<OperationDto> delete(@PathVariable("id") Long id) {

		transactionService.delete(id);

		return ResponseEntity.ok().build();
	}
	
}
