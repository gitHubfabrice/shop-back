package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.OperationDto;
import com.fatechnologies.domaine.entity.Operation;
import com.fatechnologies.domaine.mapper.OperationMapper;
import com.fatechnologies.repository.CompteRepository;
import com.fatechnologies.repository.OperationRepository;
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
public class OperationServiceImpl implements OperationService {

	@Autowired
	private OperationRepository operationRepository;

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private OperationMapper operationMapper;

	@Override
	public OperationDto getById(Long id) {
		Optional<Operation> operation = operationRepository.findById(id);

		OperationDto dto = null;
		if (operation.isPresent()) {
			dto = operationMapper.modelToDto(operation.get());
		}

		return dto;
	}

	@Override
	public OperationDto create(OperationDto dto) {

		Operation operation = operationMapper.dtoToModele(dto);

		var compte = compteRepository.findCompteById(dto.getCompteClientId());
		var compteGeneral = compteRepository.findCompteById((long) 1);

		if (operation.getNature().equals("Achat") ) {			
			compteGeneral.setSolde(compteGeneral.getSolde() - operation.getAmount());
			
		} else if (operation.getNature().equals("Vente")) {			
			compte.setSolde(compte.getSolde() + operation.getAmount());
			compteGeneral.setSolde(compteGeneral.getSolde() + operation.getAmount());
		}

		compteRepository.saveAndFlush(compteGeneral);
		compteRepository.saveAndFlush(compte);
		operationRepository.saveAndFlush(operation);

		return operationMapper.modelToDto(operation);
	}

	@Override
	public OperationDto update(OperationDto dto) {

		var operation = operationMapper.dtoToModele(dto);
		var compteGeneral = compteRepository.findCompteById((long) 1);

		var compte = compteRepository.findCompteById(dto.getCompteClientId());
		if (operation.getNature().equals("Achat")) {
			if (dto.getMemoireNature().equals(operation.getNature()) ) {
				compteGeneral.setSolde(compteGeneral.getSolde() + dto.getAmountMemory() - operation.getAmount());
			} else {
				compteGeneral.setSolde(compteGeneral.getSolde() - dto.getAmountMemory() - operation.getAmount());
			}

		} else if (operation.getNature().equals("Vente")) {
			if (dto.getMemoireNature().equals(operation.getNature()) ) {
				compte.setSolde(compte.getSolde() - dto.getAmountMemory() + operation.getAmount());
				compteGeneral.setSolde(compteGeneral.getSolde() - dto.getAmountMemory() + operation.getAmount());
			} else {
				compte.setSolde(compte.getSolde()  + dto.getAmountMemory() + operation.getAmount());
				compteGeneral.setSolde(compteGeneral.getSolde() + dto.getAmountMemory() + operation.getAmount());
			}
		}
		compteRepository.saveAndFlush(compteGeneral);
		compteRepository.saveAndFlush(compte);
		 operationRepository.saveAndFlush(operation);
		 
		return operationMapper.modelToDto(operation);
	}

	@Override
	public void delete(Long id) {
		operationRepository.deleteById(id);

	}

	@Override
	public List<OperationDto> getAll() {
		List<Operation> operations = operationRepository.findAll();
		List<OperationDto> dtos = new ArrayList<>();

		for (Operation operation : operations) {
		var dto = operationMapper.modelToDto(operation);
			
			dto.setAmountMemory(operation.getAmount());
			dto.setMemoireNature(operation.getNature());
			
			dtos.add(dto);
		}
		return dtos;

	}

}
