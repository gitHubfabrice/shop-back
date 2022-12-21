package com.fatechnologies.security.interactor.impl;

import com.fatechnologies.security.command.CreateAuthorityCommand;
import com.fatechnologies.security.command.ModifyAuthorityCommand;
import com.fatechnologies.security.interactor.AutorisationInteractor;
import com.fatechnologies.security.port.AuthorityPort;
import com.fatechnologies.security.usecase.CreateAuthority;
import com.fatechnologies.security.usecase.ModifyAuthority;
import com.fatechnologies.security.usecase.UseCase;
import com.fatechnologies.security.usecase.UseCaseExecutor;
import org.springframework.stereotype.Service;

/**
 * Auteur: fabrice ASSAGOU 7 mars 2022
 */
@Service
public class AuthorityInteractorImpl implements AutorisationInteractor {

	private final UseCase<CreateAuthorityCommand> createAuthority;
	private final UseCase<ModifyAuthorityCommand> modifyAuthority;

	public AuthorityInteractorImpl(AuthorityPort authorityPort) {
		createAuthority = new CreateAuthority(authorityPort);
		modifyAuthority = new ModifyAuthority(authorityPort);

	}
	@Override
	public void createAuthority(CreateAuthorityCommand command) {
		 new UseCaseExecutor<CreateAuthorityCommand>().execute(createAuthority, command);
		
	}

	@Override
	public void updateAuthority(ModifyAuthorityCommand command) {
		 new UseCaseExecutor<ModifyAuthorityCommand>().execute(modifyAuthority, command);
	}

}
