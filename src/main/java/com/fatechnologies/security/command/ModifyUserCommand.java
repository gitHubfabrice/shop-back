package com.fatechnologies.security.command;

import com.fatechnologies.security.domain.entity.Profil;

import java.util.Set;
import java.util.UUID;

public record ModifyUserCommand(UUID id, String email, String gender, String username, Profil profil, String roleLabel, Set<String> authorities) implements Command {
}
