package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.CityDto;
import com.fatechnologies.domaine.entity.City;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CityMapper {


	CityDto modelToDto(final City source);
	List<CityDto> modelToDto(final List<City> sources);
	City dtoToModel(final CityDto dto);
	List<City> dtoToModel(final List<CityDto> dtos);
}
