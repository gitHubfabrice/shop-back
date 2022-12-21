package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.CityDto;

import java.util.List;
 

public interface CityService {
	
	CityDto getById(Long id);
	void save(CityDto dto);
	void delete(Long id);
	List<CityDto> getAll();
}
