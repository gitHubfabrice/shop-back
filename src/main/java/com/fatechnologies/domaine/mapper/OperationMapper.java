package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.OperationDto;
import com.fatechnologies.domaine.entity.Operation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring")
public interface OperationMapper {

	@Mappings({		
		@Mapping(target = "clientId", source = "source.client.id"),
		@Mapping(target = "clientContact", source = "source.client.contact"),
		@Mapping(target = "clientLastname", source = "source.client.lastname"),
		@Mapping(target = "clientVilleLabel", source = "source.client.ville.label"),
		@Mapping(target = "clientVillePays", source = "source.client.ville.pays"),	
		@Mapping(target = "compteClientId", source = "source.client.compte.id"),	
		
})
	OperationDto modelToDto(final Operation source);
	List<OperationDto> modelToDto(final List<Operation> sources);
	
	@Mappings({		
		@Mapping(target = "client.id", source = "dto.clientId"),
		@Mapping(target = "client.contact", source = "dto.clientContact"),
		@Mapping(target = "client.lastname", source = "dto.clientLastname"),
		@Mapping(target = "client.ville.label", source = "dto.clientVilleLabel"),
		@Mapping(target = "client.ville.pays", source = "dto.clientVillePays"),	
		@Mapping(target = "client.compte.id", source = "dto.compteClientId"),	
		
})
	Operation dtoToModele(final OperationDto dto);
	List<OperationDto> dtoToModele(final List<OperationDto> dtos);
}
