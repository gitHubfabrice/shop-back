package com.fatechnologies.controller;

import com.fatechnologies.domaine.dto.OrderDto;
import com.fatechnologies.service.OrderVendorService;
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

@RestController
@RequestMapping("shop/order-vendor")
@Getter
@Setter
@NoArgsConstructor
public class OrderVendorController {
	private Logger log = LoggerFactory.getLogger(OrderVendorController.class);

	@Autowired
	private OrderVendorService commandeService;

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderDto> create(@RequestBody OrderDto commande) {

		OrderDto orderDto = commandeService.create(commande);

		return ResponseEntity.ok().body(orderDto);
	}


	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderDto> update(@RequestBody OrderDto commande) {

		OrderDto orderDto = commandeService.update(commande);

		return ResponseEntity.ok().body(orderDto);
	}

	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDto>> getAll() {
		var orders = commandeService.getAll();
		return ResponseEntity.ok().body(orders);
	}

	@DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable("id") UUID id) {
		commandeService.delete(id);
	}
	
	@GetMapping(value = "/get-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderDto> findByOrderId(@PathVariable("id") UUID id) {
		var order = commandeService.getById(id);
		return ResponseEntity.ok().body(order);
	}

}
