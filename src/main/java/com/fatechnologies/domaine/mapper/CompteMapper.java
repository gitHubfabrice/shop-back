package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.CompteDto;
import com.fatechnologies.domaine.entity.Compte;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CompteMapper {


	CompteDto modeleToDto(final Compte source);
	List<CompteDto> modeleToDto(final List<Compte> sources);

	Compte dtoToModele(final CompteDto dto);
	List<Compte> dtoToModele(final List<CompteDto> dtos);
}
