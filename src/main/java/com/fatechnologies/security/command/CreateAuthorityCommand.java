package com.fatechnologies.security.command;


import com.fatechnologies.security.sidebar.SidebarDto;

import java.util.Set;

public record CreateAuthorityCommand(String label,
                                     String description, Set<SidebarDto> sidebars) implements Command {
}
