package com.fatechnologies.usecase;

import com.fatechnologies.command.OrderCommand;
import com.fatechnologies.common.RequestTools;
import com.fatechnologies.domaine.mapper.OrderMapper;
import com.fatechnologies.security.usecase.UseCase;
import com.fatechnologies.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author ASSAGOU FABRICE 15/04/2023
 */
public class CreateOrder implements UseCase<OrderCommand> {
  private final OrderMapper orderMapper;
  private final OrderService orderService;

  public CreateOrder(OrderService orderService) {
    this.orderMapper = OrderMapper.INSTANCE;
    this.orderService = orderService;
  }

  @Override
  @Transactional
  public void perform(OrderCommand command) {
    var dto = orderMapper.commandAddToDto(command);
    dto.setReference(RequestTools.generateId("shop_order"));
    dto.getAmount(command.articles());
    dto.setCreatedAt(LocalDateTime.now());
    orderService.save(dto);
  }


}
