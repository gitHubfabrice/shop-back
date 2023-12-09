package com.fatechnologies.security.adapter.mapper.factory;


import com.fatechnologies.security.domain.dto.UserDto;
import com.fatechnologies.security.domain.entity.UserEntity;

public class AccountFactory  implements Factory<UserEntity, UserDto> {

    @Override
    public UserDto factory(UserEntity source) {
        return new UserDto(source.getId());
    }
}
