package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.CommercialDto;

import java.util.List;

public interface CommercialService { 
	
	CommercialDto getById(Long id);
	CommercialDto create(CommercialDto commercial);
	CommercialDto update(CommercialDto commercial);
	void delete(Long id);
	List<CommercialDto> getAll();
	CommercialDto connected(String login);
	int max();
	int nbre();
}
