package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.CompteDto;
import com.fatechnologies.domaine.entity.Compte;
import com.fatechnologies.domaine.mapper.CompteMapper;
import com.fatechnologies.repository.CompteRepository;
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
	private CompteRepository compteRepository;

	@Autowired
	private CompteMapper compteMapper;

	@Override
	public CompteDto getById(Long id) {
		Optional<Compte> compte = compteRepository.findById(id);

		CompteDto dto = null;
		if (compte != null && compte.isPresent()) {
			dto = compteMapper.modeleToDto(compte.get());
		}

		return dto;
	}

	@Override
	public CompteDto create(CompteDto compteDto) {

		Compte compte = compteMapper.dtoToModele(compteDto);

		compte = compteRepository.saveAndFlush(compte);
		return compteMapper.modeleToDto(compte);
	}

	@Override
	public CompteDto update(CompteDto CompteDto) {

		Compte compte = compteMapper.dtoToModele(CompteDto);
		compte = compteRepository.saveAndFlush(compte);
		return compteMapper.modeleToDto(compte);
	}

	@Override
	public void delete(Long id) {
		compteRepository.deleteById(id);

	}

	@Override
	public List<CompteDto> getAll() {
		List<Compte> comptes = compteRepository.findAll();
		List<CompteDto> dtos = new ArrayList<>();

		for (Compte compte : comptes) {
			CompteDto dto = new CompteDto();
			dto = compteMapper.modeleToDto(compte);
			dtos.add(dto);
		}
		return dtos;

	}
}
