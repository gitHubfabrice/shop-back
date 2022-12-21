package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.ProspectDto;
import com.fatechnologies.domaine.dto.VendorDto;
import com.fatechnologies.domaine.entity.Vendor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VendorMapper {


    VendorDto modelToDto(final Vendor source);
	List<VendorDto> modelToDto(final List<Vendor> sources);
    Vendor dtoToModel(final VendorDto dto);
	List<Vendor> dtoToModel(final List<VendorDto> dtos);
	List<Vendor> dtoToModels(final List<ProspectDto> prospectDtos);
}
