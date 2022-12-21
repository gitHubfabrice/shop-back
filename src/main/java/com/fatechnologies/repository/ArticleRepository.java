package com.fatechnologies.repository;


import com.fatechnologies.domaine.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    @Query(value="SELECT max(id) FROM Article")
    int max();

    @Query(value="SELECT count(id) FROM Article")
    int nbre();
}
