package com.fatechnologies.security.port;

import com.fatechnologies.security.domain.dto.AuthorityDto;

import java.util.List;
import java.util.Optional;

public interface AuthorityPort {

    Optional<AuthorityDto> getById(long id);
    void save(AuthorityDto dto);
    void delete(long id);
    List<AuthorityDto> getAll();
}
