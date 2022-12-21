package com.fatechnologies.repository;


import com.fatechnologies.domaine.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {

    @Query(value="SELECT max(id) FROM ArticleEntity")
    int max();

    @Query(value="SELECT count(id) FROM ArticleEntity")
    int nbre();
}
