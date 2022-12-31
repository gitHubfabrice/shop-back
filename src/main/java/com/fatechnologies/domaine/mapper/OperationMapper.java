package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.OperationDto;
import com.fatechnologies.domaine.entity.OperationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface OperationMapper {

	@Mapping(target = "clientId", source = "client.id")
	@Mapping(target = "clientLastname",  source = "client.lastname")
	@Mapping(target = "clientFirstname", source = "client.firstname")
	@Mapping(target = "clientCityLabel", source = "client.city.label")
	@Mapping(target = "clientBalanceId", source = "client.balance.id")
	@Mapping(target = "userId", source = "user.id")
	@Mapping(target = "articles", ignore = true)

	OperationDto modelToDto(final OperationEntity source);
	List<OperationDto> modelToDto(final List<OperationEntity> sources);
	@Mapping(target = "user.id", source = "userId")
    OperationEntity dtoToModel(final OperationDto dto);
	List<OperationDto> dtoToModel(final List<OperationDto> dtos);
}
