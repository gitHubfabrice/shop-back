package com.fatechnologies.security.adapter.repository.impl;

import com.fatechnologies.security.adapter.mapper.AuthorityMapper;
import com.fatechnologies.security.adapter.mapper.RoleMapper;
import com.fatechnologies.security.adapter.repository.jpa.RoleJpa;
import com.fatechnologies.security.domain.dto.AuthorityDto;
import com.fatechnologies.security.domain.dto.RoleDto;
import com.fatechnologies.security.port.RolePort;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * <p>adaptateur des classes {@link RoleDto et RoleEntity}
 *
 * @author Assagou Fabrice 2022-03-06
 */
@Repository
@Transactional
public class RoleAdapter implements RolePort {

    private final RoleJpa roleJpa;
    private final RoleMapper roleMapper;
    private final AuthorityMapper authorityMapper;

    public RoleAdapter(RoleJpa roleJpa) {
        this.roleJpa = roleJpa;
        roleMapper = RoleMapper.INSTANCE;
        authorityMapper = AuthorityMapper.INSTANCE;
    }


    @Override
    public Optional<RoleDto> getById(UUID id) {
        return roleJpa.findById(id).map(roleMapper::modelToDto);
    }

    @Override
    public void save(RoleDto dto) {
        var role = roleMapper.dtoToModel(dto);
        roleJpa.save(role);

    }

    @Override
    public void delete(UUID roleId) {
        roleJpa.deleteById(roleId);
    }

    @Override
    public List<RoleDto> getAll() {

        var roles = roleJpa.findAll();
        var roleDtos = new ArrayList<RoleDto>();

        roles.forEach(role -> {
            var dto = roleMapper.modelToDto(role);
            var authDtos = new ArrayList<AuthorityDto>();
            if (!role.getAuthorities().isEmpty()) {
                role.getAuthorities().forEach(authority -> {
                    var auth = authorityMapper.modelToDto(authority);
                    authDtos.add(auth);
                });

                dto.getAuthorities().clear();
                dto.getAuthorities().addAll(authDtos);
            }
            roleDtos.add(dto);
        });
        return roleDtos;
    }
}
