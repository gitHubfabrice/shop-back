package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.TransactionDto;
import com.fatechnologies.domaine.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TransactionMapper {
	@Mapping(target ="userId", source = "user.id")
	@Mapping(target ="userLastname", source = "user.lastname")
	@Mapping(target ="userFirstname", source = "user.firstname")
	TransactionDto modelToDto(final TransactionEntity source);
	List<TransactionDto> modelToDto(final List<TransactionEntity> sources);
	@Mapping(target ="user.id", source = "userId")
	TransactionEntity dtoToModel(final TransactionDto dto);
	List<TransactionEntity> dtoToModel(final List<TransactionDto> dtos);
}
