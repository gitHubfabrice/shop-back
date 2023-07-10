package com.fatechnologies.interactor;

import com.fatechnologies.command.OrderCommand;
import com.fatechnologies.command.ValidateOrderCommand;

/**
 * @author ASSAGOU FABRICE 15/04/2023
 */
public interface OrderInteract {
  void createOrder(OrderCommand command);
  void updateOrder(OrderCommand command);
  void validate(ValidateOrderCommand command);

}
