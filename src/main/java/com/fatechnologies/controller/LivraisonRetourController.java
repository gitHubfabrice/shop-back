package com.fatechnologies.controller;

import com.fatechnologies.domaine.dto.DeliveryDto;
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
@RequestMapping("shop/livraisonRetour")
@Getter
@Setter
public class LivraisonRetourController {
	private Logger log = LoggerFactory.getLogger(LivraisonRetourController.class);

	@Autowired
	private DeliveryReturnService deliveryService;

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody DeliveryDto dto) {
		deliveryService.create(dto);
	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody DeliveryDto dto) {
		deliveryService.update(dto);
	}

	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DeliveryDto>> getAll() {
		var deliveries = deliveryService.getAll();
		return ResponseEntity.ok().body(deliveries);
	}

	@DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable("id") UUID id) {
		deliveryService.delete(id);
	}

}
