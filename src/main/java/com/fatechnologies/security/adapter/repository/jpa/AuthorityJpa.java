package com.fatechnologies.security.adapter.repository.jpa;

import com.fatechnologies.security.domain.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author Assagou Fabrice 2022-03-29
 */
public interface AuthorityJpa extends JpaRepository<Authority, Long> {

    @Query("SELECT DISTINCT a FROM Role r JOIN r.authorities a ON r.id = :roleId")
    Set<Authority> findAllByRoleId(@Param("roleId") UUID roleId);
    Optional<Authority> findByLabelIgnoreCase(String label);
}
