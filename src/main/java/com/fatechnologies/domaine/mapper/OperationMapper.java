package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.OperationDto;
import com.fatechnologies.domaine.entity.OperationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring")
public interface OperationMapper {

	@Mappings({
		@Mapping(target = "clientId", source = "client.id"),
		@Mapping(target = "clientLastname", source = "client.person.lastname"),
		@Mapping(target = "clientFirstname", source = "client.person.firstname"),
		@Mapping(target = "clientCityLabel", source = "client.city.label"),
		@Mapping(target = "clientBalanceId", source = "client.balance.id")
	})
	OperationDto modelToDto(final OperationEntity source);
	List<OperationDto> modelToDto(final List<OperationEntity> sources);

    OperationEntity dtoToModel(final OperationDto dto);
	List<OperationDto> dtoToModel(final List<OperationDto> dtos);
}
