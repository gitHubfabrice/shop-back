package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.CategoryDto;
import com.fatechnologies.domaine.entity.Category;
import com.fatechnologies.domaine.mapper.CategoryMapper;
import com.fatechnologies.repository.CategoryRepository;
import com.fatechnologies.security.exception.BasicException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public CategoryDto getById(int id) {
		var category = categoryRepository.findById(id).orElseThrow(BasicException::new);
		return categoryMapper.modeleToDto(category);
	}

	@Override
	public void save(CategoryDto categoryDto) {
		var category = categoryMapper.dtoToModele(categoryDto);
		categoryRepository.saveAndFlush(category);
	}

	@Override
	public void delete(int id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public List<CategoryDto> getAll() {
		var  categories = categoryRepository.findAll();
		List<CategoryDto> dtos = new ArrayList<>();

		for (Category category : categories) {
			CategoryDto dto;
			dto = categoryMapper.modeleToDto(category);
			dtos.add(dto);
		}
		return dtos;
	}

}
