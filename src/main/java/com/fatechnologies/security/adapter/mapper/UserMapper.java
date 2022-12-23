package com.fatechnologies.security.adapter.mapper;

import com.fatechnologies.security.adapter.mapper.factory.AccountFactory;
import com.fatechnologies.security.command.CreateUserCommand;
import com.fatechnologies.security.command.ModifyUserCommand;
import com.fatechnologies.security.domain.dto.UserDto;
import com.fatechnologies.security.domain.entity.UserEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;


@Mapper(uses = {AccountFactory.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR
        , componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Named(value = "useMe")
    @Mapping(target = "authorities", ignore = true)
    UserDto modelToDto(UserEntity source);
    UserDto commandAddToDto(CreateUserCommand source);
    UserDto commandModToDto(ModifyUserCommand source);
    UserEntity dtoToModel(UserDto dto);
    Set<UserEntity> dtosToModels(Set<UserDto> dtos);
    @IterableMapping(qualifiedByName = "useMe")
    List<UserDto> modelsToDtos(List<UserEntity> sources);
}
