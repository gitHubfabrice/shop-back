package com.fatechnologies.security.domain.dto;


import com.fatechnologies.security.sidebar.SidebarDto;

import java.util.HashSet;
import java.util.Set;


public class AuthorityDto {
    private Long id;
    private String label;
    private String description;
    private Set<SidebarDto> sidebars;

    public AuthorityDto(){

    }
    public AuthorityDto(Long id){
        this.id = id;
        sidebars = new HashSet<>();
    }

    public AuthorityDto(String label, Set<SidebarDto> menus) {
        this.label = label;
        this.sidebars = menus;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<SidebarDto> getSidebars() {
        return sidebars;
    }

    public void setSidebars(Set<SidebarDto> sidebars) {
        this.sidebars = sidebars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
