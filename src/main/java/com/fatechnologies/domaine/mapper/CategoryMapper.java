package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.CategoryDto;
import com.fatechnologies.domaine.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

	
	CategoryDto modeleToDto(final Category source);
	List<CategoryDto> modeleToDto(final List<Category> sources);
	Category dtoToModele(final CategoryDto dto);
	List<Category> dtoToModele(final List<CategoryDto> dtos);
}
