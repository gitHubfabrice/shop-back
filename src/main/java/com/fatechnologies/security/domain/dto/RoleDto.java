
package com.fatechnologies.security.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class RoleDto {
    private UUID id;
    private String label;
    private String description;
    private boolean status;
    private Set<AuthorityDto> authorities;

    public RoleDto() {
        this(UUID.randomUUID());
    }

    public RoleDto(UUID id){
        this.id = id;
        authorities = new HashSet<>();
    }

}
