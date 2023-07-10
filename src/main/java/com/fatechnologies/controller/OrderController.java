package com.fatechnologies.controller;

import com.fatechnologies.command.OrderCommand;
import com.fatechnologies.command.ValidateOrderCommand;
import com.fatechnologies.domaine.dto.OrderDto;
import com.fatechnologies.interactor.OrderInteract;
import com.fatechnologies.repository.OrderRepository;
import com.fatechnologies.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ASSAGOU FABRICE 16/04/2023
 */
@RestController
@RequestMapping("shop/order")
public class OrderController {
  private final OrderInteract orderInteract;
  private final OrderService orderService;

  private final OrderRepository orderRepository;

  public OrderController(OrderInteract orderInteract, OrderService orderService, OrderRepository orderRepository) {
    this.orderInteract = orderInteract;
    this.orderService = orderService;
    this.orderRepository = orderRepository;
  }
  @RequestMapping(value = "/get-by-id/{id}")
  public ResponseEntity<OrderDto> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(orderService.findById(id));
  }
  @RequestMapping(value = "/create")
  public ResponseEntity<?> create(@RequestBody OrderCommand command){
    orderInteract.createOrder(command);
    return ResponseEntity.ok().build();
  }

  @RequestMapping(value = "/update")
  public ResponseEntity<?> update(@RequestBody OrderCommand command){
    orderInteract.updateOrder(command);
    return ResponseEntity.ok().build();
  }

  @RequestMapping(value = "/validate", method = RequestMethod.PUT)
  public ResponseEntity<?> validate(@RequestBody ValidateOrderCommand command){
    orderInteract.validate(command);
    return ResponseEntity.ok().build();
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable Long id){
    orderRepository.deleteById(id);
    return ResponseEntity.ok().build();
  }
  @RequestMapping(value = "/get-all")
  public ResponseEntity<List<OrderDto>> getAll(){
    return ResponseEntity.ok(orderService.getAllOrder());
  }
}
