package com.fatechnologies.domaine.mapper;

import com.fatechnologies.command.OrderCommand;
import com.fatechnologies.domaine.dto.OrderDto;
import com.fatechnologies.domaine.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author ASSAGOU FABRICE 15/04/2023
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {
  OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
  @Mapping(target = "clientId", source = "client.id")
  @Mapping(target = "userId", source = "user.id")
  @Mapping(target = "clientLastname",  source = "client.lastname")
  @Mapping(target = "clientFirstname", source = "client.firstname")
  @Mapping(target = "clientCityLabel", source = "client.city.label")
  @Mapping(target = "articles", ignore = true)
  OrderDto modelToDto(final OrderEntity source);
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "clientId", source = "clientId")
  @Mapping(target = "userId", source = "userId")
  OrderDto commandAddToDto(OrderCommand command);
  OrderDto commandUpToDto(OrderCommand command);
  List<OrderDto> modelToDto(final List<OrderEntity> sources);
  @Mapping(target = "client.id", source = "clientId")
  @Mapping(target = "user.id", source = "userId")
  OrderEntity dtoToModel(final OrderDto dto);
}
