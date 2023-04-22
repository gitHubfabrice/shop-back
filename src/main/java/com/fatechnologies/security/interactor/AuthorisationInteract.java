package com.fatechnologies.security.interactor;

import com.fatechnologies.security.command.CreateAuthorityCommand;
import com.fatechnologies.security.command.ModifyAuthorityCommand;

/**
 * Auteur: fabrice ASSAGOU 7 mars 2022
 */
public interface AuthorisationInteract {

	void createAuthority(CreateAuthorityCommand command);
	void updateAuthority(ModifyAuthorityCommand command);
}
