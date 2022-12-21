package com.fatechnologies.security.adapter.repository.jpa;

import com.fatechnologies.security.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Assagou Fabrice 2022-03-29
 */
public interface RoleJpa extends JpaRepository<Role, UUID> {

    Optional<Role> findOneByLabelIgnoreCase(String login);
}
