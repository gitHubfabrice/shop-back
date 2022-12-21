package com.fatechnologies.security.interactor;

import com.fatechnologies.security.command.CreateUserCommand;
import com.fatechnologies.security.command.ModifyUserCommand;

public interface UserInteractor {

  void createAccount(CreateUserCommand command);
  void CreateUser   (CreateUserCommand command);
  void updateAccount(ModifyUserCommand command);
}
