package com.fatechnologies.interactor;

import com.fatechnologies.command.OrderCommand;
import com.fatechnologies.command.ValidateOrderCommand;
import com.fatechnologies.repository.AccountBankRepository;
import com.fatechnologies.security.usecase.UseCase;
import com.fatechnologies.security.usecase.UseCaseExecutor;
import com.fatechnologies.service.OperationService;
import com.fatechnologies.service.OrderService;
import com.fatechnologies.usecase.CreateOrder;
import com.fatechnologies.usecase.UpdateOrder;
import com.fatechnologies.usecase.ValidateOrder;
import org.springframework.stereotype.Service;

/**
 * @author ASSAGOU FABRICE 15/04/2023
 */
@Service
public class OrderInteractImpl implements OrderInteract{
  private final UseCase<OrderCommand> create;
  private final UseCase<OrderCommand> update;

  private final UseCase<ValidateOrderCommand> validate;


  public OrderInteractImpl(OrderService orderService,
                           AccountBankRepository accountBankRepository, OperationService operationService) {
    create = new CreateOrder(orderService);
    update = new UpdateOrder(orderService);
    validate = new ValidateOrder(orderService, accountBankRepository, operationService);

  }

  @Override
  public void createOrder(OrderCommand command) {
    new UseCaseExecutor<OrderCommand>().execute(create, command);

  }

  @Override
  public void updateOrder(OrderCommand command) {
    new UseCaseExecutor<OrderCommand>().execute(update, command);
  }

  @Override
  public void validate(ValidateOrderCommand command) {
    new UseCaseExecutor<ValidateOrderCommand>().execute(validate,command);
  }
}
