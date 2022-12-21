/**
 * 
 */
package com.fatechnologies.security.usecase;

import com.fatechnologies.security.command.ModifyUserCommand;
import com.fatechnologies.security.domain.dto.UserDto;
import com.fatechnologies.security.port.UserPort;


/**
 * Auteur: fabrice ASSAGOU
 * Date: 22 févr. 2022
 */
public class ModifyUserIn implements UseCase<ModifyUserCommand>{

	private final UserPort userPort;

	public ModifyUserIn(UserPort userPort) {
		this.userPort = userPort;
	}
	
	@Override
	public void perform(ModifyUserCommand command) {
		var user = new UserDto();
        user.setId(command.id());
        user.setProfil(command.profil());
        user.getAuthoritiesString(). clear();
        user.getAuthoritiesString().addAll(command.authorities());
        user.setEmail(command.email());
        user.setUsername(command.username());
        user.setProfil(command.profil());
        user.setRoleLabel(command.roleLabel());
        user.setGender(command.gender());
		userPort.save(user);
		
	}

}
