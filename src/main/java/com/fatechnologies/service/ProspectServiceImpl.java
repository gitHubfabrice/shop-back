package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.ProspectDto;
import com.fatechnologies.domaine.entity.BalanceEntity;
import com.fatechnologies.domaine.entity.ProspectEntity;
import com.fatechnologies.domaine.mapper.ProspectMapper;
import com.fatechnologies.repository.AccountBankRepository;
import com.fatechnologies.repository.BalanceRepository;
import com.fatechnologies.repository.ProspectRepository;
import com.fatechnologies.security.exception.BasicException;
import com.fatechnologies.security.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
	private BalanceRepository balanceRepository;
	@Autowired
	private ProspectMapper prospectMapper;
	@Override
	public ProspectDto getById(Long id) {
		var prospect = prospectRepository.findById(id).orElseThrow(BasicException::new);
		return prospectMapper.modelToDto(prospect);
	}

	@Override
	public void save(ProspectDto prospectDto) {

		var prospect = prospectMapper.dtoToModel(prospectDto);
		prospect.setLastname(Constants.toUpperCase(prospect.getLastname()));
		prospect.setFirstname(Constants.toUpperCase(prospect.getFirstname()));
		prospect.setReference(prospectDto.getReference() != null ?  prospect.getReference() : "ART-ELED000" + idGen());
		if (prospectDto.getBalanceId() == null){
			var balance = new BalanceEntity();
			prospect.setBalance(balance);
		}
		prospectRepository.saveAndFlush(prospect);
	}

	@Override
	public void delete(Long id) {
		prospectRepository.deleteById(id);

	}

	@Override
	public List<ProspectDto> getAll() {
		List<ProspectEntity> prospectEntities = prospectRepository.findAll();
		List<ProspectDto> dtos = new ArrayList<>();
		for (ProspectEntity p : prospectEntities) {
			var  balance = balanceRepository.findById(p.getBalance().getId()).orElseThrow(BasicException::new);
			var dto = prospectMapper.modelToDto(p);
			dto.setBalanceAmount(balance.getAmount());
			dtos.add(dto);
			
		}
		dtos.sort(Comparator.comparing(ProspectDto::getBalanceAmount).thenComparing(ProspectDto::getBalanceAmount).reversed());
		return dtos;
	}


	public int idGen(){
		var nbre = prospectRepository.nbre();
		if (nbre == 0)
			return 1;
		else return prospectRepository.max() + 1;
	}
}
