package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.ProspectDto;
import com.fatechnologies.domaine.entity.ProspectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProspectMapper {
	
	@Mappings({ @Mapping(target = "cityId", source = "city.id"),
		 @Mapping(target = "cityLabel", source = "city.label"),
	    @Mapping(target = "balanceId", source = "balance.id"),
})
	ProspectDto modelToDto(final ProspectEntity source);
	List<ProspectDto> modelToDto(final List<ProspectEntity> sources);
	
	@Mappings({ @Mapping(target = "city.id", source = "cityId"),
		 @Mapping(target = "city.label", source = "cityLabel"),
	    @Mapping(target = "balance.id", source = "balanceId"),
})
    ProspectEntity dtoToModel(final ProspectDto dto);
	List<ProspectEntity> dtoToModel(final List<ProspectDto> dtos);
}
