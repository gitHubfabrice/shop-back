package com.fatechnologies.security.adapter.repository.jpa;

import com.fatechnologies.security.domain.entity.UserEntity;
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
public interface UserJpa extends JpaRepository<UserEntity, UUID> {
    String USERS_BY_USERNAME_CACHE = "UsersByUsername";
    String USERS_BY_EMAIL_CACHE = "UsersByEmail";

    Optional<UserEntity> findOneByActivationKey(String activationKey);
    Optional<UserEntity> findOneByEmailIgnoreCase(String email);
    Optional<UserEntity> findOneByUsernameIgnoreCase(String username);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_USERNAME_CACHE)
    Optional<UserEntity> findOneWithAuthoritiesByUsername(String username);

    @EntityGraph(attributePaths = "authorities")
    @Cacheable(cacheNames = USERS_BY_EMAIL_CACHE)
    Optional<UserEntity> findOneWithAuthoritiesByEmailIgnoreCase(String email);

    Page<UserEntity> findAllByIdNotNullAndActivatedIsTrue(Pageable pageable);
}
