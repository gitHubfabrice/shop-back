package com.fatechnologies.domaine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public  abstract class  Person {
    private String lastname;
    private String firstname;
    private String gender;
    private String adresse;
    private String email;
    private String contact;
    private String function;
}
