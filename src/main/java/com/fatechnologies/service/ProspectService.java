package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.ProspectDto;

import java.util.List;
 

public interface ProspectService {
	
	ProspectDto getById(Long id);
	ProspectDto create(ProspectDto prospect);
	ProspectDto update(ProspectDto prospect);
	void delete(Long id);
	List<ProspectDto> getAll();
	int max();
	int nbre();
}
