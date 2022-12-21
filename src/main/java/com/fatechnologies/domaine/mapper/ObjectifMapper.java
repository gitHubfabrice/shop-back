package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.ObjectifDto;
import com.fatechnologies.domaine.entity.Objectif;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ObjectifMapper {

	
	ObjectifDto modeleToDto(final Objectif source);
	List<ObjectifDto> modeleToDto(final List<Objectif> sources);
	Objectif dtoToModele(final ObjectifDto dto);
	List<ObjectifDto> dtoToModele(final List<ObjectifDto> dtos);
}
