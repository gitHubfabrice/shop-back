/**
 * 
 */
package com.fatechnologies.security.usecase;

import com.fatechnologies.security.command.ModifyRoleCommand;
import com.fatechnologies.security.port.RolePort;


/**
 * Auteur: fabrice ASSAGOU
 * Date: 22 févr. 2022
 */
public class ModifyRole implements UseCase<ModifyRoleCommand>{

	private final RolePort rolePort;

	public ModifyRole(RolePort rolePort) {
		this.rolePort = rolePort;
	}
	
	@Override
	public void perform(ModifyRoleCommand command) {
		
		var role = rolePort.getById(command.id()).orElseThrow();
		role.setLabel(command.label());
        role.setStatus(command.status());
        role.setAuthorities(command.authorities());
        role.setDescription(command.description());


		this.rolePort.save(role);
		
	}


}
