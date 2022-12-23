/**
 * 
 */
package com.fatechnologies.security.usecase;

import com.fatechnologies.security.adapter.mapper.UserMapper;
import com.fatechnologies.security.command.CreateUserCommand;
import com.fatechnologies.security.port.UserPort;

/**
 * Auteur: fabrice ASSAGOU Date: 22 févr. 2022
 */
public class CreateUserIn implements UseCase<CreateUserCommand> {

	private final UserPort userPort;

	private final UserMapper userMapper;

	public CreateUserIn(UserPort userPort) {
		this.userPort = userPort;
		userMapper = UserMapper.INSTANCE;
	}

	@Override
	public void perform(CreateUserCommand command) {
		var user = userMapper.commandAddToDto(command);
		userPort.registerAccount(user, command.password());
	}
}
