package com.fatechnologies.domaine.mapper;

import com.fatechnologies.command.ArticleCommand;
import com.fatechnologies.domaine.dto.ArticleDto;
import com.fatechnologies.domaine.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

	ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);
	@Mapping(target = "categoryId", source = "source.category.id")
	@Mapping(target = "categoryLabel", source = "source.category.label")
	@Mapping(target = "files", ignore = true)
	ArticleDto modelToDto(final ArticleEntity source);
	@Mapping(target = "id", ignore = true)
	ArticleDto commandAddToDto(ArticleCommand command);
	ArticleDto commandUpToDto(ArticleCommand command);
	List<ArticleDto> modelToDto(final List<ArticleEntity> sources);
	
	@Mapping(target = "category.id", source = "dto.categoryId")
	@Mapping(target = "category.label", source = "dto.categoryLabel")
    ArticleEntity dtoToModel(final ArticleDto dto);
}
