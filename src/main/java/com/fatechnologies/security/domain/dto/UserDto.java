package com.fatechnologies.security.domain.dto;

import com.fatechnologies.security.domain.entity.Profil;
import com.fatechnologies.security.domain.entity.User;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UserDto {
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
    private Profil profil;
    private Set<AuthorityDto> authorities;
    private Set<String> authoritiesString;
    private Set<PasswordHistoricDto> passwordHistorics;

    public UserDto(){
        this(UUID.randomUUID());
        this.authorities = new HashSet<>();
        this.authoritiesString = new HashSet<>();
    }

    public UserDto(UUID id){
        this.id = id;
        authorities = new HashSet<>();
        passwordHistorics = new HashSet<>();
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getLastDateModificationMotPasse() {
        return lastDateModificationMotPasse;
    }

    public void setLastDateModificationMotPasse(Instant lastDateModificationMotPasse) {
        this.lastDateModificationMotPasse = lastDateModificationMotPasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getPasswordOld() {
        return passwordOld;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String getRoleLabel() {
        return roleLabel;
    }

    public void setRoleLabel(String roleLabel) {
        this.roleLabel = roleLabel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public Set<AuthorityDto> getAuthorities() {
        return authorities;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setAuthorities(Set<AuthorityDto> authorities) {
        this.authorities = authorities;
    }

    public Set<String> getAuthoritiesString() {
        return authoritiesString;
    }

    public void setAuthoritiesString(Set<AuthorityDto> authorities) {
        authorities.forEach(authority->{
            this.authoritiesString.add(authority.getLabel());
        });
    }

    public void setAuthoritiesWithString(Set<String> authoritiesWithString) {
        this.authoritiesString = authoritiesWithString;
    }

    public Set<PasswordHistoricDto> getPasswordHistorics() {
        return passwordHistorics;
    }

    public void setPasswordHistorics(Set<PasswordHistoricDto> passwordHistorics) {
        this.passwordHistorics = passwordHistorics;
    }
}
