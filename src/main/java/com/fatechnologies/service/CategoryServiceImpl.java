package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.CategoryDto;
import com.fatechnologies.domaine.entity.CategoryEntity;
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
		return categoryMapper.modelToDto(category);
	}

	@Override
	public void save(CategoryDto categoryDto) {
		var category = categoryMapper.dtoToModel(categoryDto);
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

		for (CategoryEntity category : categories) {
			CategoryDto dto;
			dto = categoryMapper.modelToDto(category);
			dtos.add(dto);
		}
		return dtos;
	}

}
