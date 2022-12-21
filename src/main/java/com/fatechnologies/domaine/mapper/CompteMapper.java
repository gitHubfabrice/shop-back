package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.CompteDto;
import com.fatechnologies.domaine.entity.AccountBank;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CompteMapper {


	CompteDto modeleToDto(final AccountBank source);
	List<CompteDto> modeleToDto(final List<AccountBank> sources);

	AccountBank dtoToModele(final CompteDto dto);
	List<AccountBank> dtoToModele(final List<CompteDto> dtos);
}
