package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.VilleDto;
import com.fatechnologies.domaine.entity.Ville;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface VilleMapper {

	
	VilleDto modeleToDto(final Ville source);
	List<VilleDto> modeleToDto(final List<Ville> sources);
	Ville dtoToModele(final VilleDto dto);
	List<Ville> dtoToModele(final List<VilleDto> dtos);
}
