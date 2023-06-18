package com.fatechnologies.domaine.mapper;

import com.fatechnologies.domaine.dto.AccountBankDto;
import com.fatechnologies.domaine.entity.AccountBankEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AccountBankMapper {
	AccountBankDto modelToDto(final AccountBankEntity source);
	List<AccountBankDto> modelToDto(final List<AccountBankEntity> sources);

	AccountBankEntity dtoToModel(final AccountBankDto dto);
	List<AccountBankEntity> dtoToModel(final List<AccountBankDto> dtos);
}
