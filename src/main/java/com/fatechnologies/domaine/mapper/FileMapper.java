package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.FileDto;
import com.fatechnologies.domaine.entity.FileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileMapper {

	FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);
	@Mapping(target = "articleId", source = "article.id")
	FileDto modelToDto(final FileEntity source);
	List<FileDto> modelToDto(final List<FileEntity> sources);
	@Mapping(target = "article.id", source = "articleId")
	FileEntity dtoToModel(final FileDto dto);
}
