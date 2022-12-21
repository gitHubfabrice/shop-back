/**
 * 
 */
package com.fatechnologies.security.usecase;

import com.fatechnologies.security.command.ModifyUserCommand;
import com.fatechnologies.security.port.UserPort;


/**
 * Auteur: fabrice ASSAGOU
 * Date: 22 févr. 2022
 */
public class ModifyUser implements UseCase<ModifyUserCommand>{

	private final UserPort userPort;

	public ModifyUser(UserPort userPort) {
		this.userPort = userPort;
	}
	
	@Override
	public void perform(ModifyUserCommand command) {
		var user = userPort.getById(command.id());
        user.setProfil(command.profil());
        user.setAuthorities(user.getAuthorities());
        user.setProfil(command.profil());

		userPort.save(user);
		
	}

}
