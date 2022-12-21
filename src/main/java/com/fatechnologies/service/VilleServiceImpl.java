package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.VilleDto;
import com.fatechnologies.domaine.entity.Ville;
import com.fatechnologies.domaine.mapper.VilleMapper;
import com.fatechnologies.repository.VilleRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter
@Getter
@Service
@Transactional
public class VilleServiceImpl implements VilleService {

	@Autowired
	private VilleRepository villeRepository;

	@Autowired
	private VilleMapper villeMapper;


	@Override
	public VilleDto getById(Long id) {
		Optional<Ville> Ville = villeRepository.findById(id);

		VilleDto dto = null;
		if (Ville != null && Ville.isPresent()) {
			dto = villeMapper.modeleToDto(Ville.get());
		}

		return dto;
	}

	@Override
	public VilleDto create(VilleDto dto) {

		Ville data = villeMapper.dtoToModele(dto);
		data = villeRepository.saveAndFlush(data);
		return villeMapper.modeleToDto(data);
	}

	@Override
	public VilleDto update(VilleDto VilleDto) {

		Ville Ville = villeMapper.dtoToModele(VilleDto);
		Ville = villeRepository.saveAndFlush(Ville);
		return villeMapper.modeleToDto(Ville);
	}

	@Override
	public void delete(Long id) {
		villeRepository.deleteById(id);

	}

	@Override
	public List<VilleDto> getAll() {
		List<Ville> villes = villeRepository.findAll();
		List<VilleDto> dtos = new ArrayList<>();
		for (Ville e : villes) {
			dtos.add(villeMapper.modeleToDto(e));
		}
		return dtos;

	}


}
