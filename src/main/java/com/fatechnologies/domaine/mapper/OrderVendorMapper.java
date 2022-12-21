package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.OrderDto;
import com.fatechnologies.domaine.entity.OrderVendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderVendorMapper {

	@Mappings({
			@Mapping(target = "vendorId", source = "source.vendor.id"),
			@Mapping(target = "vendorCode", source = "source.vendor.code"),
			@Mapping(target = "vendorContact", source = "source.vendor.contact"),
			@Mapping(target = "vendorLastname", source = "source.vendor.lastname"),
			@Mapping(target = "vendorVilleLabel", source = "source.vendor.ville.label"),
			
	})
    OrderDto modelToDto(final OrderVendor source);
	List<OrderDto> modelToDto(final List<OrderVendor> sources);
	@Mappings({		
		@Mapping(target = "vendor.id", source = "dto.vendorId"),
		@Mapping(target = "vendor.code", source = "dto.vendorCode"),
		@Mapping(target = "vendor.contact", source = "dto.vendorContact"),
		@Mapping(target = "vendor.lastname", source = "dto.vendorLastname"),
		@Mapping(target = "vendor.ville.label", source = "dto.vendorVilleLabel"),
		
	})
    OrderVendor dtoToModel(final OrderDto dto);
	List<OrderDto> dtoToModels(final List<OrderDto> dtos);

}
