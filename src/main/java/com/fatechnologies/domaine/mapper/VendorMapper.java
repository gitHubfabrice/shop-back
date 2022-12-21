package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.VendorDto;
import com.fatechnologies.domaine.dto.ProspectDto;
import com.fatechnologies.domaine.entity.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendorMapper {

	@Mappings({ @Mapping(target = "villeId", source = "source.ville.id"),
		 @Mapping(target = "villeLabel", source = "source.ville.label"),
	    @Mapping(target = "villePays", source = "source.ville.pays"),	
	    @Mapping(target = "villeCodePays", source = "source.ville.codePays"),
})
    VendorDto modelToDto(final Vendor source);
	List<VendorDto> modelToDto(final List<Vendor> sources);
	@Mappings({ @Mapping(target = "ville.id", source = "dto.villeId"),
		 @Mapping(target = "ville.label", source = "dto.villeLabel"),
	    @Mapping(target = "ville.pays", source = "dto.villePays"),	
	    @Mapping(target = "ville.codePays", source = "dto.villeCodePays"),
})
    Vendor dtoToModel(final VendorDto dto);
	List<Vendor> dtoToModel(final List<VendorDto> dtos);
	List<Vendor> dtoToModels(final List<ProspectDto> prospectDtos);
}
