package com.fatechnologies.security.adapter.mapper.factory;


import com.fatechnologies.security.domain.dto.UserDto;
import com.fatechnologies.security.domain.entity.User;

public class AccountFactory  implements Factory<User, UserDto> {


    @Override
    public UserDto factory(User source) {
        return new UserDto(source.getId());
    }
}
