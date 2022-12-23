package com.fatechnologies.security.command;

import com.fatechnologies.security.domain.entity.Profil;

import java.util.Set;

public record CreateUserCommand(String email, String password, String gender, String username, String lastname,
                                String firstname, String contact, String function, String status,
                                Profil profil, String roleLabel, Set<String> authoritiesString) implements Command{

}
