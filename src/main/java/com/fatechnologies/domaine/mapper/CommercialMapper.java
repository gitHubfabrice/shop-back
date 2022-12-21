package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.CommercialDto;
import com.fatechnologies.domaine.entity.Commercial;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CommercialMapper {

	
	CommercialDto modeleToDto(final Commercial source);
	List<CommercialDto> modeleToDto(final List<Commercial> sources);
	Commercial dtoToModele(final CommercialDto dto);
	List<CommercialDto> dtoToModele(final List<CommercialDto> dtos);
}
