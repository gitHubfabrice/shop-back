package com.fatechnologies.security.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
@Getter
@Setter
@MappedSuperclass
public abstract class Profil implements Serializable {

  private String lastname;
  private String firstname;
  private String contact;
  private String status;
  private String function;
  private LocalDate birthday;


    @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Profil)) {
      return false;
    }
    Profil profil = (Profil) o;
    return Objects.equals(lastname, profil.lastname) &&
        Objects.equals(firstname, profil.firstname) &&
        Objects.equals(function, profil.function) &&
        Objects.equals(birthday, profil.birthday) ;
  }

  @Override
  public int hashCode() {
    return Objects.hash(lastname, firstname, function, birthday);
  }

  @Override
  public String toString() {
    return "Profil{" +
        "nom='" + lastname + '\'' +
        ", prenom='" + firstname + '\'' +
        ", fonction='" + function + '\'' +
        ", dateNaissance=" + birthday +
        '}';
  }
}
