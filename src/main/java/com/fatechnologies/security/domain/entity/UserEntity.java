package com.fatechnologies.security.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatechnologies.domaine.entity.*;
import com.fatechnologies.security.utils.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Clock;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "shop_secure_user")
public class UserEntity extends Profile implements Serializable {

  @Id
  private UUID id;
  @NotNull
  @Pattern(regexp = Constants.LOGIN_REGEX)
  @Size(min = 1, max = 50)
  @Column(length = 50, unique = true, nullable = false)
  private String username;
  private String password;
  private String gender;
  private Instant lastDateUpdatePassword;
  @Email
  @Size(min = 5, max = 254)
  @Column(length = 254, unique = true)
  private String email;
  private boolean activated;
  private boolean locked;
  private boolean connected;
  @Size(max = 20)
  @Column(name = "activation_key", length = 20)
  @JsonIgnore
  private String activationKey;
  @ManyToOne
  private Role role;
  @Size(min = 2, max = 10)
  @Column(name = "lang_key", length = 10)
  private String langKey;

  @ManyToMany()
  @JoinTable(
          name = "shop_secure_user_authority",
          joinColumns = { @JoinColumn(name = "user_id") },
          inverseJoinColumns = { @JoinColumn(name = "authority_id") }
  )
  private Set<Authority> authorities;

  @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Set<PasswordHistoric> passwordHistoricals;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "balance_id", referencedColumnName = "id")
  private BalanceEntity balance;

  @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Set<TransactionEntity> transactions;

  @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Set<CategoryEntity> categories;

  @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Set<OperationEntity> operations;

  @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Set<ProspectEntity> clients;

  @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Set<CityEntity> cities;

  public UserEntity() {
    this.authorities = new HashSet<>();
    this.passwordHistoricals = new HashSet<>();
    this.balance = new BalanceEntity();
  }

  public void addAuthority(Authority authority) {
    boolean estContenu = this.authorities.contains(authority);
    if (!estContenu) {
      this.authorities.add(authority);
    }
  }

  public Set<String> getAuthorisations() {
    Set<String> permissions = this.authorities.stream()
        .map(Authority::getLabel).collect(Collectors.toSet());
    var rolePermissions = role.getAuthorities()
        .stream()
        .map(Authority::getLabel)
        .collect(Collectors.toSet());
    permissions.addAll(rolePermissions);
    return permissions;
  }

  public PasswordHistoric historicalPassword(String newPassword) {
    boolean dejaUtilise = isUsed(newPassword);
    if (!dejaUtilise) {
      PasswordHistoric passwordHistoric = new PasswordHistoric(this.password,Instant.now(
          Clock.systemUTC()));
      passwordHistoric.setUser(this);
      return passwordHistoric;
    }
    throw new PasswordUsed();
  }



    private boolean isUsed(String newPassword) {
    return this.passwordHistoricals.stream()
        .map(PasswordHistoric::getPassword)
        .anyMatch(ancienMotPasse -> ancienMotPasse.equals(newPassword));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UserEntity that)) {
      return false;
    }
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(id, username, password, lastDateUpdatePassword, activated, locked, connected,
            role, passwordHistoricals);
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", lastDateUpdatePassword=" + lastDateUpdatePassword +
        ", activated=" + activated +
        ", locked=" + locked +
        ", connected=" + connected +
        ", roles=" + role +
        ", passwordHistoricals=" + passwordHistoricals +
        '}';
  }
}
