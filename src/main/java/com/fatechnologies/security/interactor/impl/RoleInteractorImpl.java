package com.fatechnologies.security.interactor.impl;

import com.fatechnologies.security.command.CreateRoleCommand;
import com.fatechnologies.security.command.ModifyRoleCommand;
import com.fatechnologies.security.interactor.RoleInteractor;
import com.fatechnologies.security.port.RolePort;
import com.fatechnologies.security.usecase.CreateRole;
import com.fatechnologies.security.usecase.ModifyRole;
import com.fatechnologies.security.usecase.UseCase;
import com.fatechnologies.security.usecase.UseCaseExecutor;
import org.springframework.stereotype.Service;

@Service
public class RoleInteractorImpl implements RoleInteractor {

	private final UseCase<CreateRoleCommand> createRole;
	private final UseCase<ModifyRoleCommand> modifyRole;




	public RoleInteractorImpl(final RolePort rolePort) {
		createRole = new CreateRole(rolePort);
		modifyRole = new ModifyRole(rolePort);
	}

	@Override
	public void createRole(final CreateRoleCommand command) {
		new UseCaseExecutor<CreateRoleCommand>().execute(createRole, command);
	}

	@Override
	public void updateRole(final ModifyRoleCommand command) {
		new UseCaseExecutor<ModifyRoleCommand>().execute(modifyRole, command);
		
	}
}
