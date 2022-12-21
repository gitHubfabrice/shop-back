/**
 * 
 */
package com.fatechnologies.security.usecase;

import com.fatechnologies.security.command.CreateUserCommand;
import com.fatechnologies.security.domain.dto.UserDto;
import com.fatechnologies.security.port.UserPort;

/**
 * Auteur: fabrice ASSAGOU Date: 22 févr. 2022
 */
public class CreateUserIn implements UseCase<CreateUserCommand> {

	private final UserPort userPort;

	public CreateUserIn(UserPort userPort) {
		this.userPort = userPort;
	}

	@Override
	public void perform(CreateUserCommand command) {
		var user = new UserDto();
        user.setUsername(command.email());
        user.setEmail(command.email());
        user.setPassword(command.password());
        user.setGender(command.gender());
        user.setProfil(command.profil());
        user.setRoleLabel(command.roleLabel());
		userPort.registerAccount(user, command.password());
	}
}
