package com.fatechnologies.security.command;

import com.fatechnologies.security.domain.dto.AuthorityDto;

import java.util.Set;
import java.util.UUID;

public record ModifyRoleCommand(UUID id, String label, String description
        , boolean status, Set<AuthorityDto> authorities) implements Command {
}
