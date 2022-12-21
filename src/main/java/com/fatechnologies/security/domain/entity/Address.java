package com.fatechnologies.security.domain.entity;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * <p>Objet valeur contenant les informations d'une adresse.</p>
 *
 * @author Christian Amani 2020-07-03
 */

public class Address implements Serializable {

  protected String adressePostale;
  protected String adresseGeographique;
  protected String telephone1;
  protected String telephone2;

  @Email
  @Size(min = 5, max = 254)
  @Column(length = 254, unique = true)
  protected String email;
  protected String emailSecondaire;
  protected String fax;
  protected String webSite;
  protected String boitePostale;

  public Address() {
  }

  public Address(String adressePostale, String telephone1, String email, String fax) {
    this.adressePostale = adressePostale;
    this.telephone1 = telephone1;
    this.email = email;
    this.fax = fax;
  }

  public String getAdressePostale() {
    return adressePostale;
  }

  public void setAdressePostale(String adressePostale) {
    this.adressePostale = adressePostale;
  }

  public String getAdresseGeographique() {
    return adresseGeographique;
  }

  public void setAdresseGeographique(String adresseGeographique) {
    this.adresseGeographique = adresseGeographique;
  }

  public String getTelephone1() {
    return telephone1;
  }

  public void setTelephone1(String telephone1) {
    this.telephone1 = telephone1;
  }

  public String getTelephone2() {
    return telephone2;
  }

  public void setTelephone2(String telephone2) {
    this.telephone2 = telephone2;
  }

  public String getWebSite() {
    return webSite;
  }

  public void setWebSite(String webSite) {
    this.webSite = webSite;
  }

  public String getBoitePostale() {
    return boitePostale;
  }

  public void setBoitePostale(String boitePostale) {
    this.boitePostale = boitePostale;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmailSecondaire() {
    return emailSecondaire;
  }

  public void setEmailSecondaire(String emailSecondaire) {
    this.emailSecondaire = emailSecondaire;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Address)) {
      return false;
    }
    Address adress = (Address) o;
    return Objects.equals(adressePostale, adress.adressePostale) &&
        Objects.equals(adresseGeographique, adress.adresseGeographique) &&
        Objects.equals(telephone1, adress.telephone1) &&
        Objects.equals(telephone2, adress.telephone2) &&
        Objects.equals(email, adress.email) &&
        Objects.equals(emailSecondaire, adress.emailSecondaire) &&
        Objects.equals(fax, adress.fax) &&
        Objects.equals(webSite, adress.webSite) &&
        Objects.equals(boitePostale, adress.boitePostale);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(adressePostale, adresseGeographique, telephone1, telephone2, email, emailSecondaire,
            fax, webSite, boitePostale);
  }

  @Override
  public String toString() {
    return "Adresse{" +
        "adressePostale='" + adressePostale + '\'' +
        ", adresseGeographique='" + adresseGeographique + '\'' +
        ", telephone1='" + telephone1 + '\'' +
        ", telephone2='" + telephone2 + '\'' +
        ", email='" + email + '\'' +
        ", emailSecondaire='" + emailSecondaire + '\'' +
        ", fax='" + fax + '\'' +
        ", webSite='" + webSite + '\'' +
        ", boitePostale='" + boitePostale + '\'' +
        '}';
  }
}
