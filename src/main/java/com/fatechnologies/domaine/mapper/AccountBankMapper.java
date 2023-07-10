package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.AccountBankDto;
import com.fatechnologies.domaine.entity.AccountBankEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AccountBankMapper {
	AccountBankMapper INSTANCE = Mappers.getMapper(AccountBankMapper.class);
	AccountBankDto modelToDto(final AccountBankEntity source);
	List<AccountBankDto> modelToDto(final List<AccountBankEntity> sources);

	AccountBankEntity dtoToModel(final AccountBankDto dto);
	List<AccountBankEntity> dtoToModel(final List<AccountBankDto> dtos);
}
