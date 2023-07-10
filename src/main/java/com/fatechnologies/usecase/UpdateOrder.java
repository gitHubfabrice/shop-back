package com.fatechnologies.usecase;

import com.fatechnologies.command.OrderCommand;
import com.fatechnologies.domaine.mapper.OrderMapper;
import com.fatechnologies.security.usecase.UseCase;
import com.fatechnologies.service.OrderService;

import java.time.LocalDateTime;

/**
 * @author ASSAGOU FABRICE 15/04/2023
 */
public class UpdateOrder implements UseCase<OrderCommand> {
  private final OrderMapper orderMapper;
  private final OrderService orderService;

  public UpdateOrder(OrderService orderService) {
    this.orderMapper = OrderMapper.INSTANCE;
    this.orderService = orderService;
  }

  @Override
  public void perform(OrderCommand command) {
    var dto = orderMapper.commandUpToDto(command);
    dto.setUpdatedAt(LocalDateTime.now());
    orderService.save(dto);
  }
}
