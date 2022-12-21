package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.VilleDto;

import java.util.List;
 

public interface VilleService {
	
	VilleDto getById(Long id);
	VilleDto create(VilleDto dto);
	VilleDto update(VilleDto dto);
	void delete(Long id);
	List<VilleDto> getAll();
}
