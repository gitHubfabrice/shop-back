package com.fatechnologies.security.interactor.impl;

import com.fatechnologies.security.command.CreateUserCommand;
import com.fatechnologies.security.command.ModifyUserCommand;
import com.fatechnologies.security.interactor.UserInteract;
import com.fatechnologies.security.port.UserPort;
import com.fatechnologies.security.usecase.*;
import org.springframework.stereotype.Service;

@Service
public class UserInteractImpl implements UserInteract {

	private final UseCase<CreateUserCommand> createAccount;
    private final UseCase<CreateUserCommand> createUser;
	private final UseCase<ModifyUserCommand> modifyAccount;




	public UserInteractImpl(final UserPort userPort) {
		createAccount = new CreateUserIn(userPort);
        createUser    = new CreateUser(userPort);
		modifyAccount = new ModifyUserIn(userPort);
	}

	@Override
	public void createAccount(final CreateUserCommand command) {
		new UseCaseExecutor<CreateUserCommand>().execute(createAccount, command);
	}

    @Override
    public void CreateUser(final CreateUserCommand command) {
        new UseCaseExecutor<CreateUserCommand>().execute(createUser, command);
    }

    @Override
	public void updateAccount(final ModifyUserCommand command) {
		new UseCaseExecutor<ModifyUserCommand>().execute(modifyAccount, command);
		
	}
}
