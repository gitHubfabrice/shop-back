package com.fatechnologies.security.command;

import com.fatechnologies.security.domain.dto.AuthorityDto;

import java.util.Set;

public record CreateRoleCommand(String label, String description
        ,boolean status, Set<AuthorityDto> authorities) implements Command {
}
