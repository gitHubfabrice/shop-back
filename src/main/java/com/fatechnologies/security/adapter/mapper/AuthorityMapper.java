package com.fatechnologies.security.adapter.mapper;

import com.fatechnologies.security.adapter.mapper.factory.AuthorityFactory;
import com.fatechnologies.security.domain.dto.AuthorityDto;
import com.fatechnologies.security.domain.entity.Authority;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(uses = {AuthorityFactory.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR
        , componentModel = "spring")
public interface AuthorityMapper {
    AuthorityMapper INSTANCE = Mappers.getMapper(AuthorityMapper.class);
    @Named(value = "useMe")
    @Mapping(target = "sidebars", ignore = true)
    AuthorityDto modelToDto(Authority source);
    Authority dtoToModel(AuthorityDto dto);
    Set<Authority> dtosToModels(Set<AuthorityDto> dtos);
    @IterableMapping(qualifiedByName = "useMe")
    List<AuthorityDto> modelsToDtos(List<Authority> sources);
}
