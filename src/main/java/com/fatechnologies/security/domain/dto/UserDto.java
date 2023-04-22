package com.fatechnologies.security.domain.dto;

import com.fatechnologies.security.domain.entity.Profile;
import com.fatechnologies.security.domain.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Getter
@Setter
public class UserDto extends Profile {
    private UUID id;
    private String username;
    private String password;
    private String newPassword;
    private String passwordOld;
    private Instant lastDateModificationMotPasse;
    private String email;
    private boolean activated;
    private boolean locked;
    private boolean connected;
    private String roleLabel;
    private String gender;
    private Set<AuthorityDto> authorities;
    private Set<String> authoritiesString;
    private Set<PasswordHistoricDto> passwordHistoricals;

    public UserDto(){
        this(UUID.randomUUID());
        this.authorities = new HashSet<>();
        this.authoritiesString = new HashSet<>();
    }

    public UserDto(UUID id){
        this.id = id;
        authorities = new HashSet<>();
        passwordHistoricals = new HashSet<>();
    }

    public UserDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
    }

}
