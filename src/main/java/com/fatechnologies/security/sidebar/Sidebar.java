package com.fatechnologies.security.sidebar;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "shop_menu")
public class Sidebar {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String label;
    private String icon;
    private String router;
    @Column(columnDefinition = "int default 0")
    private int orderShow;
    private String description;
    @ManyToMany()
    @JoinTable(
            name               = "shop_menu_childs",
            joinColumns        = { @JoinColumn(name = "menu_id") },
            inverseJoinColumns = { @JoinColumn(name = "menu_child_id") }
    )
    private Set<Sidebar> childs;

}
