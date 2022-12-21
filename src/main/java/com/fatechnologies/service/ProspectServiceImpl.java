package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.ProspectDto;
import com.fatechnologies.domaine.entity.Compte;
import com.fatechnologies.domaine.entity.Prospect;
import com.fatechnologies.domaine.mapper.ProspectMapper;
import com.fatechnologies.repository.CompteRepository;
import com.fatechnologies.repository.ProspectRepository;
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
public class ProspectServiceImpl implements ProspectService {

	@Autowired
	private ProspectRepository prospectRepository;
	
	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private ProspectMapper prospectMapper;


	@Override
	public ProspectDto getById(Long id) {
		Optional<Prospect> prospect = prospectRepository.findById(id);

		ProspectDto dto = null;
		if (prospect != null && prospect.isPresent()) {
			dto = prospectMapper.modelToDto(prospect.get());
		}

		return dto;
	}

	@Override
	public ProspectDto create(ProspectDto prospectDto) {

		Prospect prospect = prospectMapper.dtoToModel(prospectDto);
		Compte compte = new Compte();
		compte.setCode("1000" +  prospect.getCode());
		compte.setSolde((double) 0);
		compte = compteRepository.save(compte);
		
		prospect.setCompte(compte);
		prospect = prospectRepository.saveAndFlush(prospect);
	
		
		return prospectMapper.modelToDto(prospect);
	}

	@Override
	public ProspectDto update(ProspectDto prospectDto) {

		Prospect prospect = prospectMapper.dtoToModel(prospectDto);
		prospect = prospectRepository.saveAndFlush(prospect);
		return prospectMapper.modelToDto(prospect);
	}

	@Override
	public void delete(Long id) {
		prospectRepository.deleteById(id);

	}

	@Override
	public List<ProspectDto> getAll() {
		List<Prospect> prospects = prospectRepository.findAll();
		List<ProspectDto> dtos = new ArrayList<>();
		for (Prospect e : prospects) {
			Optional<Compte> compte = compteRepository.findById(e.getCompte().getId());
			var dto = new ProspectDto();
			dto = prospectMapper.modelToDto(e);
			dto.setSolde(compte.get().getSolde());
			dtos.add(dto);
			
		}
		return dtos;

	}
	
	@Override
	public int max() {

		return prospectRepository.max();
	}

	@Override
	public int nbre() {

		return prospectRepository.nbre();
	}

}
