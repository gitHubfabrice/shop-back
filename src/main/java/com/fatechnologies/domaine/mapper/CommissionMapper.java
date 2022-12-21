package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.CommissionDto;
import com.fatechnologies.domaine.entity.Commission;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CommissionMapper {

	
	CommissionDto modeleToDto(final Commission source);
	List<CommissionDto> modeleToDto(final List<Commission> sources);
	Commission dtoToModele(final CommissionDto dto);
	List<CommissionDto> dtoToModele(final List<CommissionDto> dtos);
}
