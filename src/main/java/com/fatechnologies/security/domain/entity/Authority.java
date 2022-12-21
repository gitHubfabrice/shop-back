package com.fatechnologies.security.domain.entity;

import com.fatechnologies.security.sidebar.Sidebar;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "shop_secure_authority")
@Getter
@Setter
public class Authority implements Serializable {

  @Id
  @SequenceGenerator(name = "gen_authority", sequenceName = "seq_hotel_authority", initialValue = 1, allocationSize = 1)
  @GeneratedValue(generator = "gen_authority")
  private Long id;
  private String label;
  private String description;

    @ManyToMany()
    @JoinTable( name = "shop_secure_authority_sidebars",
            joinColumns = @JoinColumn( name = "authority_id" ),
            inverseJoinColumns = @JoinColumn( name = "sidebar_id" ))
    private Set<Sidebar> sidebars;

    public Authority() {
    }

  public Authority(String label, String description) {
    this.label = label;
    this.description = description;
  }

    @Override
    public String toString() {
        return "Authority{" +
                "name='" + label + '\'' +
                "}";
    }

}
