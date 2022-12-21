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
@RequestMapping("shop/deliver-vendor")
@Getter
@Setter
public class DeliverVendorController {
	private Logger log = LoggerFactory.getLogger(DeliverVendorController.class);

	@Autowired
	private DeliveryVendorService deliverService;

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody DeliveryDto deliver) {
		 deliverService.create(deliver);
	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody DeliveryDto deliver) {
		deliverService.update(deliver);
	}

	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DeliveryDto>> getAll() {

		List<DeliveryDto> livraisons = deliverService.getAll();

		return ResponseEntity.ok().body(livraisons);
	}

	@DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable("id") UUID id) {
		deliverService.delete(id);
	}

}
