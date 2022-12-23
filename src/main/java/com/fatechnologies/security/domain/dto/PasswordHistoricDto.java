package com.fatechnologies.security.domain.dto;

import com.fatechnologies.security.domain.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter

public class PasswordHistoricDto {

    private UUID id;
    private String password;
    private Instant dateHistorisation;
    private UserEntity userEntity;

    public PasswordHistoricDto(){
        this(UUID.randomUUID());
    }
    public PasswordHistoricDto(UUID id){
        this.id = id;
    }

}
