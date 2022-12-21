package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.ProspectDto;

import java.util.List;
 

public interface ProspectService {
	
	ProspectDto getById(Long id);
	void save(ProspectDto prospect);
	void delete(Long id);
	List<ProspectDto> getAll();
}
