package com.fatechnologies.security.command;

import java.util.Set;
import java.util.UUID;

public record ModifyUserCommand(
            UUID id, String email, String gender, String username, String lastname,
            String firstname, String contact, String function, String status,
            String roleLabel, Set<String> authoritiesString) implements Command{
}
