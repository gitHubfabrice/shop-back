package com.fatechnologies.usecase;

import com.fatechnologies.command.ValidateOrderCommand;
import com.fatechnologies.domaine.dto.*;
import com.fatechnologies.repository.AccountBankRepository;
import com.fatechnologies.security.usecase.UseCase;
import com.fatechnologies.security.utils.Constants;
import com.fatechnologies.service.OperationService;
import com.fatechnologies.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ASSAGOU FABRICE 16/04/2023
 */
public class ValidateOrder implements UseCase<ValidateOrderCommand> {
  private final OrderService orderService;
  private final AccountBankRepository accountBankRepository;
  private final OperationService operationService;
  public ValidateOrder(OrderService orderService,
                       AccountBankRepository accountBankRepository, OperationService operationService) {
    this.orderService = orderService;
    this.accountBankRepository = accountBankRepository;
    this.operationService = operationService;
  }

  @Override
  @Transactional
  public void perform(ValidateOrderCommand command) {
    var now = LocalDateTime.now();
    var order = orderService.findById(command.id());
    if (order.isStatus()){
      return;
    }

    order.setStatus(true);
    order.setValidatedAt(now);
    var account = accountBankRepository
            .findOneByReferenceIgnoreCase(Constants.ACCOUNT_PRINCIPAL)
            .orElseThrow();

    account.deposit(order.getAmount());
    accountBankRepository.saveAndFlush(account);
    createOperation(order);
    orderService.save(order);
  }


  private void createOperation(OrderDto order){

    var operation = new OperationDto();
    operation.setAmount(order.getAmount());
    operation.setCreatedAt(LocalDateTime.now());
    operation.setStatus(true);
    operation.setDate(order.getDate());
    operation.setType(TypeOperation.OUT);
    operation.setUserId(order.getUserId());
    operation.setClientId(order.getClientId());
    translate(order.getArticles());
    operation.getArticles().clear();
    operation.getArticles().addAll(order.getArticles());
    operationService.outStock(operation);
  }

  private void translate(List<ArticleDto> articles){
    for (ArticleDto article : articles) {
      article.setQuantityArtDel(article.getQuantityArtOrd());
      article.setPriceArtDel(article.getPriceArtOrd());
    }
  }

}
