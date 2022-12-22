package com.fatechnologies.domaine.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class  Person {
    private String lastname;
    private String firstname;
    private String adresse;
    private String email;
    private String contact;
    private String function;
}
