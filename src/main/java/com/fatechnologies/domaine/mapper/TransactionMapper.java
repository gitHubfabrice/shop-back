package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.TransactionDto;
import com.fatechnologies.domaine.entity.TransactionEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TransactionMapper {

	TransactionDto modelToDto(final TransactionEntity source);
	List<TransactionDto> modelToDto(final List<TransactionEntity> sources);

	TransactionEntity dtoToModel(final TransactionDto dto);
	List<TransactionEntity> dtoToModel(final List<TransactionDto> dtos);
}
