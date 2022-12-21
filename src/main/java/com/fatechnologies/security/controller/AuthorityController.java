package com.fatechnologies.security.controller;

import com.fatechnologies.security.adapter.mapper.AuthorityMapper;
import com.fatechnologies.security.adapter.repository.jpa.AuthorityJpa;
import com.fatechnologies.security.command.CreateAuthorityCommand;
import com.fatechnologies.security.command.ModifyAuthorityCommand;
import com.fatechnologies.security.domain.dto.AuthorityDto;
import com.fatechnologies.security.interactor.impl.AuthorityInteractorImpl;
import com.fatechnologies.security.port.AuthorityPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author Assagou Fabrice 2022-03-06
 */
@RestController
@RequestMapping("/shop/authority")
public class AuthorityController {

	private final AuthorityJpa authorityJpa;
    private final AuthorityInteractorImpl authorityInteractor;
    private final AuthorityPort authorityPort;
    private final AuthorityMapper authorityMapper;

	public AuthorityController(AuthorityJpa authorityJpa, AuthorityInteractorImpl authorityInteractor, AuthorityPort authorityPort) {
        this.authorityJpa = authorityJpa;
        this.authorityInteractor = authorityInteractor;
        this.authorityPort = authorityPort;authorityMapper = AuthorityMapper.INSTANCE;
    }

    @GetMapping(value = "/get-by-id/{id}")
    public AuthorityDto getById(@Valid @PathVariable("id") long id) {
        var authority = authorityJpa.findById(id).map(authorityMapper::modelToDto);
        return authority.get();
    }


	@DeleteMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void deleteAuthority( @PathVariable("id") long id) {
		authorityJpa.deleteById(id);
	}

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createAuthority( @RequestBody CreateAuthorityCommand command){
        authorityInteractor.createAuthority(command);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateAuthority( @RequestBody ModifyAuthorityCommand command) {
        authorityInteractor.updateAuthority(command);
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuthorityDto>> getAll() {
        return ResponseEntity.accepted().body(authorityPort.getAll());
    }

}
