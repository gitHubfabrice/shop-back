package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.CommercialDto;
import com.fatechnologies.domaine.dto.CommissionDto;
import com.fatechnologies.domaine.entity.Commercial;
import com.fatechnologies.domaine.entity.CommercialCommission;
import com.fatechnologies.domaine.entity.Commission;
import com.fatechnologies.domaine.mapper.CommercialMapper;
import com.fatechnologies.domaine.mapper.CommissionMapper;
import com.fatechnologies.domaine.mapper.ObjectifMapper;
import com.fatechnologies.repository.CommercialRepository;
import com.fatechnologies.repository.CommissionRepository;
import com.fatechnologies.repository.ObjectifRepository;
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
public class CommercialServiceImpl implements CommercialService {

	@Autowired
	private CommercialRepository commercialRepository;

	@Autowired
	private CommercialMapper commercialMapper;

	@Autowired
	private ObjectifMapper objectifMapper;

	@Autowired
	private CommissionMapper commissionMapper;

	@Autowired
	private ObjectifRepository objectifRepository;

	@Autowired
	private CommissionRepository commissionRepository;

	@Override
	public CommercialDto getById(Long id) {
		Optional<Commercial> commercial = commercialRepository.findById(id);

		CommercialDto dto = null;
		if (commercial != null && commercial.isPresent()) {
			dto = commercialMapper.modeleToDto(commercial.get());
		}

		return dto;
	}

	@Override
	public CommercialDto create(CommercialDto commercialDto) {

		Commercial commercial = commercialMapper.dtoToModele(commercialDto);
		// List<CommercialObjectif> comObj = new ArrayList<CommercialObjectif>();
		List<CommercialCommission> comCom = new ArrayList<CommercialCommission>();
		// for(ObjectifDto obj:commercialDto.getObjectifs()) {
		// Optional<Objectif> objectifOptional =
		// this.objectifRepository.findById(obj.getId());
		// if(objectifOptional.isPresent()) {
		// CommercialObjectif co = new CommercialObjectif();
		// co.setCommercial(commercial);
		// co.setObjectif(objectifOptional.get());
		// co.setDateDebut(obj.getDateDebut());
		// co.setDateFin(obj.getDateFin());
		// comObj.add(co);
		// }
		// }

		for (CommissionDto com : commercialDto.getCommissions()) {
			Optional<Commission> commissionOptional = this.commissionRepository.findById(com.getId());
			if (commissionOptional.isPresent()) {
				CommercialCommission cc = new CommercialCommission();
				cc.setCommercial(commercial);
				cc.setommission(commissionOptional.get());
				cc.setDateDebut(com.getDateDebut());
				cc.setDateFin(com.getDateFin());
				comCom.add(cc);
			}
		}
		// commercial.getCommercialObjectifs().clear();
		commercial.getCommercialCommissions().clear();
		// commercial.getCommercialObjectifs().addAll(comObj);
		commercial.getCommercialCommissions().addAll(comCom);
		commercial = commercialRepository.saveAndFlush(commercial);
		return commercialMapper.modeleToDto(commercial);
	}

	@Override
	public CommercialDto update(CommercialDto commercialDto) {

		Commercial commercial = commercialMapper.dtoToModele(commercialDto);
		// List<CommercialObjectif> comObj = new ArrayList<CommercialObjectif>();
		List<CommercialCommission> comCom = new ArrayList<CommercialCommission>();
		// for(ObjectifDto obj:commercialDto.getObjectifs()) {
		// Optional<Objectif> objectifOptional =
		// this.objectifRepository.findById(obj.getId());
		// if(objectifOptional.isPresent()) {
		// CommercialObjectif co = new CommercialObjectif();
		// co.setCommercial(commercial);
		// co.setObjectif(objectifOptional.get());
		// co.setDateDebut(obj.getDateDebut());
		// co.setDateFin(obj.getDateFin());
		// comObj.add(co);
		// }
		// }
		for (CommissionDto com : commercialDto.getCommissions()) {
			Optional<Commission> commissionOptional = this.commissionRepository.findById(com.getId());
			if (commissionOptional.isPresent()) {
				CommercialCommission cc = new CommercialCommission();
				cc.setCommercial(commercial);
				cc.setommission(commissionOptional.get());
				cc.setDateDebut(com.getDateDebut());
				cc.setDateFin(com.getDateFin());
				comCom.add(cc);
			}
		}
		// commercial.getCommercialObjectifs().clear();
		commercial.getCommercialCommissions().clear();
		// commercial.getCommercialObjectifs().addAll(comObj);
		commercial.getCommercialCommissions().addAll(comCom);
		commercial = commercialRepository.saveAndFlush(commercial);
		return commercialMapper.modeleToDto(commercial);
	}

	@Override
	public void delete(Long id) {
		commercialRepository.deleteById(id);

	}

	@Override
	public List<CommercialDto> getAll() {
		List<Commercial> commerciaux = commercialRepository.findAll();
		List<CommercialDto> dtos = new ArrayList<>();
		for (Commercial cm : commerciaux) {
			CommercialDto dto = commercialMapper.modeleToDto(cm);
			// for(CommercialObjectif co : cm.getCommercialObjectifs()) {
			// ObjectifDto obj = objectifMapper.modeleToDto(co.getObjectif());
			// obj.setDateDebut(co.getDateDebut());
			// obj.setDateFin(co.getDateFin());
			// dto.getObjectifs().add(obj);
			// }
			// dtos.add(dto);
			//
			for (CommercialCommission co : cm.getCommercialCommissions()) {
				CommissionDto com = commissionMapper.modeleToDto(co.getCommission());
				com.setDateDebut(co.getDateDebut());
				com.setDateFin(co.getDateFin());
				dto.getCommissions().add(com);
			}
			dtos.add(dto);
		}
		return dtos;

	}

	@Override
	public CommercialDto connected(String login) {
		if (login == null) {
			return new CommercialDto();
		}

		List<Commercial> commercials = commercialRepository.findByLogin(login);

		if (commercials == null || commercials.isEmpty() || commercials.size() > 1) {
			return new CommercialDto();
		}

		CommercialDto commercialDto = commercialMapper.modeleToDto(commercials.get(0));

		return commercialDto;
	}
	
	@Override
	public int max() {

		return commercialRepository.max();
	}

	@Override
	public int nbre() {

		return commercialRepository.nbre();
	}

}
