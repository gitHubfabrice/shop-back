package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.ArticleDto;

import java.util.List;

public interface ArticleService {
	 
	ArticleDto getById(int id);
	void save(ArticleDto article);
	void delete(int id);
	List<ArticleDto> getAll();
	double getStockValue();
	int idGen();
	void updateInventory(String action,Integer articleId, int quantityTemp, int quantity);
}
