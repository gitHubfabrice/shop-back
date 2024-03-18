package com.fatechnologies.security.adapter.repository.impl;

import com.fatechnologies.security.adapter.mapper.AuthorityMapper;
import com.fatechnologies.security.adapter.repository.jpa.AuthorityJpa;
import com.fatechnologies.security.domain.dto.AuthorityDto;
import com.fatechnologies.security.port.AuthorityPort;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
*
* @author Assagou Fabrice 2022-03-06
*/
@Repository
@Transactional
public class AuthorityAdapter implements AuthorityPort {

    private final AuthorityJpa authorityJpa;
    private final AuthorityMapper authorityMapper;



    public AuthorityAdapter(AuthorityJpa authorityJpa) {
        this.authorityJpa = authorityJpa;
        this.authorityMapper = AuthorityMapper.INSTANCE;
    }

    @Override
    public Optional<AuthorityDto> getById(long id) {
        return authorityJpa.findById(id).map(authorityMapper::modelToDto);
    }

    @Override
    public void save(AuthorityDto dto) {

        var authority = authorityMapper.dtoToModel(dto);
        authorityJpa.save(authority);
    }

    @Override
    public void delete(long id) {
        authorityJpa.deleteById(id);
    }

    @Override
    public List<AuthorityDto> getAll() {
        var authorities = authorityJpa.findAll();
        return authorityMapper.modelsToDtos(authorities);
    }
}
