package com.fatechnologies.security.domain.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * <p>Objet valeur représentant les information d'un profil.</p>
 *
 * @author Christian Amani 2020-07-03
 */
@Embeddable
public class Profil implements Serializable {

  private String lastname;
  private String firstname;
  private String contact;
  private String status;
  private String function;
  private LocalDate birthday;


  public Profil() {
  }

  public Profil(String lastname, String firstname, String contact, String status, String function, LocalDate birthday) {
    this.lastname = lastname;
    this.firstname = firstname;
    this.contact = contact;
    this.status = status;
    this.function = function;
    this.birthday = birthday;
  }

  public String getFunction() {
    return function;
  }

  public void setFunction(String function) {

    this.function = function;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }



  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getFirstname() {
    return firstname;
  }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
