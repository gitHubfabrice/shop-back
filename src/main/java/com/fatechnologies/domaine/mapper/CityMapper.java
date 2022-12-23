package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.CityDto;
import com.fatechnologies.domaine.entity.CityEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CityMapper {


	CityDto modelToDto(final CityEntity source);
	List<CityDto> modelToDto(final List<CityEntity> sources);
	CityEntity dtoToModel(final CityDto dto);
	List<CityEntity> dtoToModel(final List<CityDto> dtos);
}
