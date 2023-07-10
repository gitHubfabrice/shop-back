package com.fatechnologies.security.command;

import com.fatechnologies.security.domain.entity.Profile;

import java.util.Set;

public record CreateUserCommand(String email, String password, String gender, String username, String lastname,
                                String firstname, String contact, String function, String status,
                                Profile profile, String roleLabel, Set<String> authoritiesString) implements Command{

}
