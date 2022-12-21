package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.ProspectDto;
import com.fatechnologies.domaine.entity.AccountBank;
import com.fatechnologies.domaine.entity.ProspectEntity;
import com.fatechnologies.domaine.mapper.ProspectMapper;
import com.fatechnologies.repository.AccountBankRepository;
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
	private AccountBankRepository accountBankRepository;

	@Autowired
	private ProspectMapper prospectMapper;


	@Override
	public ProspectDto getById(Long id) {
		Optional<ProspectEntity> prospect = prospectRepository.findById(id);

		ProspectDto dto = null;
		if (prospect != null && prospect.isPresent()) {
			dto = prospectMapper.modelToDto(prospect.get());
		}

		return dto;
	}

	@Override
	public ProspectDto create(ProspectDto prospectDto) {

		ProspectEntity prospectEntity = prospectMapper.dtoToModel(prospectDto);
		AccountBank accountBank = new AccountBank();
		accountBank.setCode("1000" +  prospectEntity.getCode());
		accountBank.setAmount((double) 0);
		accountBank = accountBankRepository.save(accountBank);
		
		prospectEntity.setCompte(accountBank);
		prospectEntity = prospectRepository.saveAndFlush(prospectEntity);
	
		
		return prospectMapper.modelToDto(prospectEntity);
	}

	@Override
	public ProspectDto update(ProspectDto prospectDto) {

		ProspectEntity prospectEntity = prospectMapper.dtoToModel(prospectDto);
		prospectEntity = prospectRepository.saveAndFlush(prospectEntity);
		return prospectMapper.modelToDto(prospectEntity);
	}

	@Override
	public void delete(Long id) {
		prospectRepository.deleteById(id);

	}

	@Override
	public List<ProspectDto> getAll() {
		List<ProspectEntity> prospectEntities = prospectRepository.findAll();
		List<ProspectDto> dtos = new ArrayList<>();
		for (ProspectEntity e : prospectEntities) {
			Optional<AccountBank> compte = accountBankRepository.findById(e.getCompte().getId());
			var dto = new ProspectDto();
			dto = prospectMapper.modelToDto(e);
			dto.setSolde(compte.get().getAmount());
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
