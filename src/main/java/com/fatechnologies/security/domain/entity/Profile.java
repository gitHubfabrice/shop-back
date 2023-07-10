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
public abstract class Profile implements Serializable {

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
    if (!(o instanceof Profile profile)) {
      return false;
    }
      return Objects.equals(lastname, profile.lastname) &&
        Objects.equals(firstname, profile.firstname) &&
        Objects.equals(function, profile.function) &&
        Objects.equals(birthday, profile.birthday) ;
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
