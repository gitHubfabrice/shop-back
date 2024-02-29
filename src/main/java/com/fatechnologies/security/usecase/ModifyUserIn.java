/**
 * 
 */
package com.fatechnologies.security.usecase;

import com.fatechnologies.security.adapter.mapper.UserMapper;
import com.fatechnologies.security.command.ModifyUserCommand;
import com.fatechnologies.security.port.UserPort;


/**
 * Auteur: fabrice ASSAGOU
 * Date: 22 févr. 2022
 */
public class ModifyUserIn implements UseCase<ModifyUserCommand>{

	private final UserPort userPort;
	private final UserMapper userMapper;

	public ModifyUserIn(UserPort userPort) {
		this.userPort = userPort;
		userMapper = UserMapper.INSTANCE;
	}
	
	@Override
	public void perform(ModifyUserCommand command) {
		var user = userMapper.commandModToDto(command);
		userPort.updateUser(user);
	}
}
