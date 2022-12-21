package com.fatechnologies.security.adapter.repository.jpa;

import com.fatechnologies.security.domain.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Assagou Fabrice 2022-03-29
 */
public interface UserJpa extends JpaRepository<User, UUID> {
    String USERS_BY_USERNAME_CACHE = "UsersByUsername";
    String USERS_BY_EMAIL_CACHE = "UsersByEmail";

    Optional<User> findOneByActivationKey(String activationKey);
    Optional<User> findOneByEmailIgnoreCase(String email);
    Optional<User> findOneByUsernameIgnoreCase(String username);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_USERNAME_CACHE)
    Optional<User> findOneWithAuthoritiesByUsername(String username);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
    Optional<User> findOneWithAuthoritiesByEmailIgnoreCase(String email);

    Page<User> findAllByIdNotNullAndActivatedIsTrue(Pageable pageable);
}
