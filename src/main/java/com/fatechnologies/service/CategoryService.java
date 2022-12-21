package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

	CategoryDto getById(int id);
	void save(CategoryDto category);
	void delete(int id);
	List<CategoryDto> getAll();


}
