package com.fatechnologies.security.adapter.mapper.factory;

import com.fatechnologies.security.domain.dto.RoleDto;
import com.fatechnologies.security.domain.entity.Role;

public class RoleFactory implements Factory<Role, RoleDto> {

    @Override
    public RoleDto factory(Role source) {
        return new RoleDto(source.getId());
    }
}
