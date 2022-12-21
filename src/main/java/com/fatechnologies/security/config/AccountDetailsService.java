package com.fatechnologies.security.config;

import com.fatechnologies.security.adapter.repository.jpa.UserJpa;
import com.fatechnologies.security.domain.entity.User;
import com.fatechnologies.security.service.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AccountDetailsService implements UserDetailsService {

    private final UserJpa userJpa;

    public AccountDetailsService(UserJpa userJpa) {
        this.userJpa = userJpa;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userJpa.findOneByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("L'identifiant n'existe pas: " + username));
        return UserDetailsImpl.build(user);
    }

}
