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
        user.setAuthorities(user.getAuthorities());
        user.setFirstname(command.firstname());
        user.setLastname(command.lastname());
        user.setStatus(command.status());
        user.setContact(command.contact());
        user.setFunction(command.function());
        user.setFirstname(command.firstname());
		user.setRoleLabel(command.roleLabel());

		userPort.save(user);
		
	}

}
