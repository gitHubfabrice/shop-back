package com.fatechnologies.domaine.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public  class  Person  implements Serializable {
    private String lastname;
    private String firstname;
    private String adresse;
    private String email;
    private String contact;
    private String function;
}
