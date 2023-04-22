package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.OrderDto;

import java.util.List;

/**
 * @author ASSAGOU FABRICE 15/04/2023
 */
public interface OrderService{
  OrderDto findById(Long id);
  void save(OrderDto dto);
  void delete(Long id);
  List<OrderDto> getAllOrder();

}
