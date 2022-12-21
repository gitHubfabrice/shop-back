package com.fatechnologies.security.adapter.mapper.factory;

import com.fatechnologies.security.domain.dto.AuthorityDto;
import com.fatechnologies.security.domain.entity.Authority;

public class AuthorityFactory implements Factory<Authority, AuthorityDto> {

    @Override
    public AuthorityDto factory(Authority source) {
        return new AuthorityDto(source.getId());
    }
}
