package com.fatechnologies.security.domain.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * <p>Entité domaine représentant les informations d'un rôle.</p>*
 *
 * @author Christian Amani 2020-07-03
 */
@Entity
@Table(name = "shop_secure_role")
@NoArgsConstructor
public class Role implements Serializable {
  @Id
  private UUID id;
  @NotNull
  @Column(length = 50, unique = true, nullable = false)
  private String label;
  private String description;
  private boolean status;
  @ManyToMany()
  @JoinTable( name = "shop_secure_role_authorities",
            joinColumns = @JoinColumn( name = "role_id" ),
            inverseJoinColumns = @JoinColumn( name = "authority_label" ))
  private Set<Authority> authorities;


  public Role(String label, String description) {
    this.label = label;
    this.description = description;
    this.authorities = new HashSet<>();
  }

    public Role(UUID id) {
      this.id = id;
    }

    public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }


  public void addAuthority(Authority authority) {
    boolean contain = authorities.contains(authority);
    if (!contain) {
      authorities.add(authority);
    }
  }

  public void deleteAuthority(Authority authority) {
    if (authority != null) {
      authorities.remove(authority);
    }
  }

  public boolean ownAuthority(Authority authority) {
    return authorities.contains(authority);
  }




  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Role)) {
      return false;
    }
    Role that = (Role) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, label, description, status, authorities);
  }

  @Override
  public String toString() {
    return "Role{" +
        "id=" + id +
        ", label='" + label + '\'' +
        ", description='" + description + '\'' +
        ", status=" + status +
        ", authorities=" + authorities +
        '}';
  }
}
