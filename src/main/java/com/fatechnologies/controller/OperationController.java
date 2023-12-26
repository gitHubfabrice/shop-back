package com.fatechnologies.controller;

import com.fatechnologies.domaine.dto.OperationDto;
import com.fatechnologies.service.OperationService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("shop/operation")
@Getter
@Setter

public class OperationController {
	private Logger log = LoggerFactory.getLogger(OperationController.class);

	@Autowired
	private OperationService operationService;

	@GetMapping(value = "/get-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OperationDto> getById(@PathVariable("id") UUID id) {
		var dtos = operationService.getById(id);
		return ResponseEntity.ok().body(dtos);
	}

	@PostMapping(value = "/in-stock", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void inStock(@RequestBody OperationDto data) {
		operationService.inStock(data);
	}
	
	@PutMapping(value = "/out-stock", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void outStock(@RequestBody OperationDto data) {
		operationService.outStock(data);
	}



	@GetMapping(value = "/get-all-in", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OperationDto>> getAllIn() {
		List<OperationDto> dtos = operationService.getAllInStockHistory();
		return ResponseEntity.ok().body(dtos);
	}


	/*@GetMapping(value = "/get-all-out", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OperationDto>> getAllOut() {
		List<OperationDto> dtos = operationService.getAllOutStockHistory();
		return ResponseEntity.ok().body(dtos);
	}*/

	@GetMapping(value = "/get-all-out", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<OperationDto>> getAllIn(@Param("page") int page, @Param("itemsPerPage") int itemsPerPage) {
		var dtos = operationService.getAllOutStockHistory(page, itemsPerPage);
		return ResponseEntity.ok().body(dtos);
	}

	@DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable("id") UUID id) {
		operationService.delete(id);
	}
	
}
