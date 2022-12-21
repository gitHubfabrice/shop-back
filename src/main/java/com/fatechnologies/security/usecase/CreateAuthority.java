/**
 * 
 */
package com.fatechnologies.security.usecase;

import com.fatechnologies.security.command.CreateAuthorityCommand;
import com.fatechnologies.security.domain.dto.AuthorityDto;
import com.fatechnologies.security.port.AuthorityPort;

/**
 * Auteur: fabrice ASSAGOU Date: 22 févr. 2022
 */
public class CreateAuthority implements UseCase<CreateAuthorityCommand> {

	private final AuthorityPort authorityPort;

	public CreateAuthority(AuthorityPort authorityPort) {
		this.authorityPort = authorityPort;
	}

	@Override
	public void perform(CreateAuthorityCommand command) {
		var authority = new AuthorityDto();
        authority.setLabel(command.label());
        authority.setDescription(command.description());
        authority.setSidebars(command.sidebars());

		authorityPort.save(authority);
	}
}
