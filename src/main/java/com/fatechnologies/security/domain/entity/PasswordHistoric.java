package com.fatechnologies.security.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * <p>Entité domaine représentant les informations d'un mot de passe historisé.</p>
 * <p>Un mot de passe historisé est un mot de passe qui a été déjà utilisé.</p>
 *
 * @author Christian Amani 2020-07-03
 */
@Entity
@Table(name = "shop_secure_password_historical")
@Getter
@Setter
public class PasswordHistoric implements Serializable {

    @Id
    private UUID id;
    private String password;
    private Instant dateHistorisation;
    @ManyToOne
    private User user;

    public PasswordHistoric() {
        this(UUID.randomUUID());
    }

    public PasswordHistoric(UUID id) {
        this.id = id;
    }

    public PasswordHistoric(String password, Instant dateHistorisation) {
        this.password = password;
        this.dateHistorisation = dateHistorisation;
    }

}
