package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.DeliveryDto;
import com.fatechnologies.domaine.entity.DeliveryVendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliveryVendorMapper {

	@Mappings({
			@Mapping(target = "vendorId", source = "source.vendor.id"),
			@Mapping(target = "vendorCode", source = "source.vendor.code"),
			@Mapping(target = "vendorLastname", source = "source.vendor.lastname"),
			@Mapping(target = "vendorContact", source = "source.vendor.contact")

	})
	DeliveryDto modelToDto(final DeliveryVendor source);
	List<DeliveryDto> modelToDto(final List<DeliveryVendor> sources);
	@Mappings({
		@Mapping(target = "vendor.id", source = "source.vendorId"),
		@Mapping(target = "vendor.code", source = "source.vendorCode"),
		@Mapping(target = "vendor.lastname", source = "source.vendorLastname"),
		@Mapping(target = "vendor.contact", source = "source.vendorContact")

})
    DeliveryVendor dtoToModel(final DeliveryDto source);
	List<DeliveryDto> dtoToModel(final List<DeliveryDto> dtos);
}
