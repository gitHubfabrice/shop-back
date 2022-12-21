package com.fatechnologies.security.sidebar;

import java.util.List;
import java.util.UUID;

public interface SidebarService {
    SidebarDto create(SidebarDto dto);
    SidebarDto update(SidebarDto dto);
    List<SidebarDto> getAll();
    List<SidebarDto> getAllPrincipal();
    List<SidebarDto> getAllByAccountId(UUID accountId);
}
