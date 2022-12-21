package com.fatechnologies.security.sidebar;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(uses = { SidebarFactory.class })
public interface SidebarMapper {

    SidebarMapper INSTANCE = Mappers.getMapper(SidebarMapper.class);

    @Named(value = "useMe")
    @Mapping(target = "childs", ignore = true)
    SidebarDto modelToDto(Sidebar source);
    Sidebar dtoToModel(SidebarDto dto);
    Set<Sidebar> dtosToModels(Set<SidebarDto> dtos);

    @IterableMapping(qualifiedByName = "useMe")
    List<SidebarDto> modelsToDtos(List<Sidebar> sources);
}
