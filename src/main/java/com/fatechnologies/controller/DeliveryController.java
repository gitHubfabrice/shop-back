package com.fatechnologies.controller;

import com.fatechnologies.domaine.dto.DeliveryDto;
import com.fatechnologies.service.DeliveryService;
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
@RequestMapping("shop/delivery")
@Getter
@Setter
public class DeliveryController {
	private Logger log = LoggerFactory.getLogger(DeliveryController.class);

	@Autowired
	private DeliveryService deliveryService;

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody DeliveryDto delivery) {
		deliveryService.create(delivery);
	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody DeliveryDto delivery) {
		deliveryService.update(delivery);
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
