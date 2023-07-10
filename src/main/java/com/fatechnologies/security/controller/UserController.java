package com.fatechnologies.security.controller;

import com.fatechnologies.security.command.CreateUserCommand;
import com.fatechnologies.security.command.ModifyUserCommand;
import com.fatechnologies.security.domain.dto.UserDto;
import com.fatechnologies.security.interactor.UserInteract;
import com.fatechnologies.security.port.UserPort;
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
@RequestMapping("/shop/user")
public class UserController {

	private final UserInteract userInteract;
	private final UserPort userPort;

	public UserController(UserInteract userInteract, UserPort userPort) {
		this.userInteract = userInteract;
        this.userPort = userPort;
	}

	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void newAccount( @RequestBody CreateUserCommand command) {
		userInteract.createAccount(command);
	}

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateAccount( @RequestBody ModifyUserCommand command) {
        userInteract.updateAccount(command);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteAccount( @PathVariable("id") UUID id) {
        userPort.delete(id);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void newUser( @RequestBody CreateUserCommand command) {
        userInteract.CreateUser(command);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser( @RequestBody CreateUserCommand command) {
        userInteract.CreateUser(command);
    }

    @PutMapping(value = "/update-password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updatePassword( @RequestBody UserDto userDto) {
        userPort.updatePassword(userDto);
    }

	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDto>> getAll() {
        var accounts = userPort.getAll();
		return ResponseEntity.accepted().body(accounts);
	}

    @GetMapping(value = "/get-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getById(@PathVariable("id") UUID id) {
        var account = userPort.getById(id);
        return ResponseEntity.accepted().body(account);
    }

}