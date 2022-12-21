package com.fatechnologies.controller;

import com.fatechnologies.domaine.dto.OrderDto;
import com.fatechnologies.service.OrderService;
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
@RequestMapping("shop/commande")
@Getter
@Setter
@NoArgsConstructor
public class OrderController {
	private Logger log = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody OrderDto commande) {
		orderService.create(commande);

	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody OrderDto commande) {
		orderService.update(commande);
	}

	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDto>> getAll() {
		List<OrderDto> orders = orderService.getAll();
		return ResponseEntity.ok().body(orders);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<OrderDto> delete(@PathVariable("id") UUID id) {
		orderService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "/get-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderDto> findByOrderId(@PathVariable("id") UUID id) {

		var order = orderService.getById(id);
		return ResponseEntity.ok().body(order);
	}
	
	@GetMapping(value = "/get-by-status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderDto>> findByOrderStatus(@PathVariable("status") String status) {
		List<OrderDto> orders = orderService.getByStatus(status);
		return ResponseEntity.ok().body(orders);
	}

}
