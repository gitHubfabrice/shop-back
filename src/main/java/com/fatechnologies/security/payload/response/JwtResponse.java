package com.fatechnologies.security.payload.response;

import com.fatechnologies.security.domain.entity.Profil;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class JwtResponse {
	private String token;
	private String type = "Bearer";
    private Profil profil;
	private String lastname;
	private String firstname;
    private String username;
	private UUID id;
	private String email;
	private String status;
	private List<String> roles;

	public JwtResponse(String token,
					   UUID id,
					   String username,
					   String lastname,
					   String firstname,
					   String email,
					   String status,
					   List<String> roles
	) {
		this.token = token;
		this.id = id;
		this.username = username;
		this.lastname = lastname;
		this.firstname = firstname;
		this.status = status;
		this.email = email;
		this.roles = roles;
	}

}
