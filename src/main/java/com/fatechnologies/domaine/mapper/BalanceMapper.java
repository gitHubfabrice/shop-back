package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.BalanceDto;
import com.fatechnologies.domaine.entity.BalanceEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BalanceMapper {

	
	BalanceDto modelToDto(final BalanceEntity source);
	List<BalanceDto> modelToDto(final List<BalanceEntity> sources);
	BalanceEntity dtoToModel(final BalanceDto dto);
	List<BalanceEntity> dtoToModel(final List<BalanceDto> dtos);
}
