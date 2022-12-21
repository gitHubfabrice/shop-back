package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.CompteDto;

import java.util.List;

public interface CompteService {
	 
	CompteDto getById(Long id);
	CompteDto create(CompteDto compte);
	CompteDto update(CompteDto compte);
	void delete(Long id);
	List<CompteDto> getAll();



}
