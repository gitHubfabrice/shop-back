package com.fatechnologies.security.interactor;

import com.fatechnologies.security.command.CreateRoleCommand;
import com.fatechnologies.security.command.ModifyRoleCommand;

public interface RoleInteractor {

	void createRole(CreateRoleCommand command);
	void updateRole(ModifyRoleCommand command);
}
