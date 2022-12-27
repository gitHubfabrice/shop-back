package com.fatechnologies.domaine.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "shop_file")
@Getter
@Setter
public class FileEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private String type;
    private String filename;
    private String url;
    private boolean available;

    @ManyToOne(fetch = FetchType.LAZY)
    private ArticleEntity article;
}

