package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.ArticleDto;

import java.util.List;

public interface ArticleService {
	 
	ArticleDto getById(int id);
	void create(ArticleDto article);
	void update(ArticleDto article);
	void delete(int id);
	List<ArticleDto> getAll();



}
