/**
 * 
 */
package com.fatechnologies.security.usecase;

import com.fatechnologies.security.command.CreateRoleCommand;
import com.fatechnologies.security.domain.dto.RoleDto;
import com.fatechnologies.security.port.RolePort;

/**
 * Auteur: fabrice ASSAGOU Date: 28 févr. 2022
 */
public class CreateRole implements UseCase<CreateRoleCommand> {

	private final RolePort rolePort;

	public CreateRole(RolePort rolePort) {
		this.rolePort = rolePort;
	}

	@Override
	public void perform(CreateRoleCommand command) {

		var role = new RoleDto();
        role.setLabel(command.label());
        role.setStatus(command.status());
        role.setDescription(command.description());
        role.getAuthorities().clear();
        role.getAuthorities().addAll(command.authorities());

		this.rolePort.save(role);
	}
}
