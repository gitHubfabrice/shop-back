package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.DeliveryDto;
import com.fatechnologies.domaine.entity.DeliveryReturn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliveryReturnMapper {

	@Mappings({
			@Mapping(target = "clientCode", source = "source.client.code"),
			@Mapping(target = "clientLastname", source = "source.client.lastname")

	})
    DeliveryDto modelToDto(final DeliveryReturn source);
	List<DeliveryDto> modelToDto(final List<DeliveryReturn> sources);
	DeliveryReturn dtoToModel(final DeliveryDto dto);
	List<DeliveryDto> dtoToModel(final List<DeliveryDto> dtos);
}
