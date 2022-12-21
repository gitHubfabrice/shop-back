package com.fatechnologies.security.port;

import com.fatechnologies.security.domain.dto.RoleDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RolePort {

    Optional<RoleDto> getById(UUID id);
    void save(RoleDto dto);
    void delete(UUID roleId);
    List<RoleDto> getAll();
}