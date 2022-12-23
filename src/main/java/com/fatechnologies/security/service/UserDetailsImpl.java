package com.fatechnologies.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatechnologies.security.domain.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
	@Serial
	private static final long serialVersionUID = 1L;
	private UUID id;
	private String username;
	private String firstname;
	private String lastname;
	private String status;
	private String email;
	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(UUID id, String username, String lastname, String firstname, String status, String email, String password,
						   Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.lastname = lastname;
		this.firstname = firstname;
		this.status = status;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(UserEntity userEntity) {
		List<SimpleGrantedAuthority> authorities = userEntity.getAuthorities().stream()
				.map(role -> new SimpleGrantedAuthority(role.getLabel()))
				.toList();


		return new UserDetailsImpl(
				userEntity.getId(),
				userEntity.getUsername(),
				userEntity.getLastname(),
				userEntity.getFirstname(),
				userEntity.getStatus(),
				userEntity.getEmail(),
				userEntity.getPassword(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getUsername(), getEmail(), getPassword(), getAuthorities());
	}
}