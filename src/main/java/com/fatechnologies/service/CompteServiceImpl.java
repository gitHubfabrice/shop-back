package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.CompteDto;
import com.fatechnologies.domaine.entity.AccountBank;
import com.fatechnologies.domaine.mapper.CompteMapper;
import com.fatechnologies.repository.AccountBankRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
@Transactional
public class CompteServiceImpl implements CompteService {

	@Autowired
	private AccountBankRepository accountBankRepository;

	@Autowired
	private CompteMapper compteMapper;

	@Override
	public CompteDto getById(Long id) {
		Optional<AccountBank> compte = accountBankRepository.findById(id);

		CompteDto dto = null;
		if (compte != null && compte.isPresent()) {
			dto = compteMapper.modeleToDto(compte.get());
		}

		return dto;
	}

	@Override
	public CompteDto create(CompteDto compteDto) {

		AccountBank accountBank = compteMapper.dtoToModele(compteDto);

		accountBank = accountBankRepository.saveAndFlush(accountBank);
		return compteMapper.modeleToDto(accountBank);
	}

	@Override
	public CompteDto update(CompteDto CompteDto) {

		AccountBank accountBank = compteMapper.dtoToModele(CompteDto);
		accountBank = accountBankRepository.saveAndFlush(accountBank);
		return compteMapper.modeleToDto(accountBank);
	}

	@Override
	public void delete(Long id) {
		accountBankRepository.deleteById(id);

	}

	@Override
	public List<CompteDto> getAll() {
		List<AccountBank> accountBanks = accountBankRepository.findAll();
		List<CompteDto> dtos = new ArrayList<>();

		for (AccountBank accountBank : accountBanks) {
			CompteDto dto = new CompteDto();
			dto = compteMapper.modeleToDto(accountBank);
			dtos.add(dto);
		}
		return dtos;

	}
}
