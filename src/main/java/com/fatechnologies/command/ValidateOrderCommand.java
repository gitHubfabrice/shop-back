package com.fatechnologies.command;

import com.fatechnologies.security.command.Command;

/**
 * @author ASSAGOU FABRICE 15/04/2023
 */
public record ValidateOrderCommand(
        Long id
) implements Command {}
