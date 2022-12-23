package com.fatechnologies.security.service;

import com.fatechnologies.security.adapter.repository.jpa.UserJpa;
import com.fatechnologies.security.domain.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserJpa userJpa;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userJpa.findOneByUsernameIgnoreCase(username)
				.orElseThrow(() -> new UsernameNotFoundException("L'utilisateur n'existe pas: " + username));

		return UserDetailsImpl.build(userEntity);
	}

}
