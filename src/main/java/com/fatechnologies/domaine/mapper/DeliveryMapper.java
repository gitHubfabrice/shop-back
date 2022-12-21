package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.DeliveryDto;
import com.fatechnologies.domaine.entity.Delivery;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {


	DeliveryDto modelToDto(final Delivery source);
	List<DeliveryDto> modelToDto(final List<Delivery> sources);

	Delivery dtoToModel(final DeliveryDto dto);

	List<Delivery> dtoToModel(final List<DeliveryDto> dtos);
}
