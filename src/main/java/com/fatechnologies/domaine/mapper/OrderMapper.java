package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.ArticleDto;
import com.fatechnologies.domaine.dto.OrderDto;
import com.fatechnologies.domaine.entity.ArticleOrder;
import com.fatechnologies.domaine.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

	@Mappings({		
			@Mapping(target = "clientId", source = "client.id"),
			@Mapping(target = "clientCode", source = "client.code"),
			@Mapping(target = "clientContact", source = "client.contact"),
			@Mapping(target = "clientLastname", source = "client.lastname"),
			@Mapping(target = "clientVilleLabel", source = "client.ville.label"),
			
	})
    OrderDto modelToDto(final Order source);
	List<OrderDto> modelToDto(final List<Order> sources);
	@Mappings({		
		@Mapping(target = "client.id", source = "clientId"),
		@Mapping(target = "client.code", source = "clientCode"),
		@Mapping(target = "client.contact", source = "clientContact"),
		@Mapping(target = "client.lastname", source = "clientLastname"),
		@Mapping(target = "client.email", source = "clientEmail"),
		@Mapping(target = "client.ville.label", source = "clientVilleLabel"),
})
	Order dtoToModel(final OrderDto dto);
	List<Order> dtoToModel(final List<OrderDto> dtos);
	List<ArticleDto> dtoToModels(final List<ArticleOrder> dtos);
	List<ArticleOrder> modelsToDtos(final List<ArticleDto> dtos);


}
