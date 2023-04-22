package com.fatechnologies.security.controller;

import com.fatechnologies.security.adapter.repository.jpa.RoleJpa;
import com.fatechnologies.security.command.CreateRoleCommand;
import com.fatechnologies.security.command.ModifyRoleCommand;
import com.fatechnologies.security.domain.dto.RoleDto;
import com.fatechnologies.security.interactor.RoleInteract;
import com.fatechnologies.security.port.RolePort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author Assagou Fabrice 2022-03-06
 */
@RestController
@RequestMapping("/shop/role")
public class RoleController {

	private final RoleJpa roleJpa;
    private final RoleInteract roleInteract;
    private final RolePort rolePort;

	public RoleController(RoleJpa roleJpa, RoleInteract roleInteract, RolePort rolePort) {
		this.roleJpa = roleJpa;
        this.roleInteract = roleInteract;
        this.rolePort = rolePort;
    }


	@DeleteMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteRole( @PathVariable("id") UUID id) {
		roleJpa.deleteById(id);
	}

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createRole( @RequestBody CreateRoleCommand command){
        roleInteract.createRole(command);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateRole( @RequestBody ModifyRoleCommand command) {
        roleInteract.updateRole(command);
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoleDto>> getAll() {
        return ResponseEntity.accepted().body(rolePort.getAll());
    }
}
