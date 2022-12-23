package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.CategoryDto;
import com.fatechnologies.domaine.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

	
	CategoryDto modelToDto(final CategoryEntity source);
	List<CategoryDto> modelToDto(final List<CategoryEntity> sources);
	CategoryEntity dtoToModel(final CategoryDto dto);
	List<CategoryEntity> dtoToModel(final List<CategoryDto> dtos);
}
