package com.fatechnologies.security.adapter.mapper;

import com.fatechnologies.security.adapter.mapper.factory.RoleFactory;
import com.fatechnologies.security.domain.dto.RoleDto;
import com.fatechnologies.security.domain.entity.Role;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(uses = {RoleFactory.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR
        , componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    @Named(value = "useMe")
    @Mapping(target = "authorities", ignore = true)
    RoleDto modelToDto(Role source);
    Role dtoToModel(RoleDto dto);
    Set<Role> dtosToModels(Set<RoleDto> dtos);
    @IterableMapping(qualifiedByName = "useMe")
    List<RoleDto> modelsToDtos(List<Role> sources);
}
