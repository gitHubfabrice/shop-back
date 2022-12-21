package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.ProspectDto;
import com.fatechnologies.domaine.entity.Prospect;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProspectMapper {
	
	@Mappings({ @Mapping(target = "villeId", source = "source.ville.id"),
		 @Mapping(target = "villeLabel", source = "source.ville.label"),
	    @Mapping(target = "villePays", source = "source.ville.pays"),	
	    @Mapping(target = "villeCodePays", source = "source.ville.codePays"),
	    @Mapping(target = "compteId", source = "source.compte.id"),
})
	ProspectDto modelToDto(final Prospect source);
	List<ProspectDto> modelToDto(final List<Prospect> sources);
	
	@Mappings({ @Mapping(target = "ville.id", source = "dto.villeId"),
		 @Mapping(target = "ville.label", source = "dto.villeLabel"),
	    @Mapping(target = "ville.pays", source = "dto.villePays"),	
	    @Mapping(target = "ville.codePays", source = "dto.villeCodePays"),
	    @Mapping(target = "compte.id", source = "dto.compteId"),
})
	Prospect dtoToModel(final ProspectDto dto);
	List<Prospect> dtoToModel(final List<ProspectDto> dtos);
}
