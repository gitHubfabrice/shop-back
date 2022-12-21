/**
 * 
 */
package com.fatechnologies.security.usecase;

import com.fatechnologies.security.command.ModifyAuthorityCommand;
import com.fatechnologies.security.port.AuthorityPort;


/**
 * Auteur: fabrice ASSAGOU
 * Date: 22 févr. 2022
 */
public class ModifyAuthority implements UseCase<ModifyAuthorityCommand>{

	private final AuthorityPort authorityPort;

	public ModifyAuthority(AuthorityPort authorityPort) {
		this.authorityPort = authorityPort;
	}
	
	@Override
	public void perform(ModifyAuthorityCommand command) {
		var authority = authorityPort.getById(command.id()).orElseThrow();
        authority.setLabel(command.label());
        authority.setDescription(command.description());
        authority.setSidebars(command.sidebars());

		authorityPort.save(authority);
		
	}

}
