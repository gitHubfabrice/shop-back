package com.fatechnologies.security.command;

import com.fatechnologies.security.domain.entity.Profil;

import java.util.Set;

public record CreateUserCommand(String username, String password, String roleLabel, String email, String gender, Profil profil, Set<String> authorities) implements Command {

}
