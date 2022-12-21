package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.VendorDto;

import java.util.List;

public interface VendorService {
	
	VendorDto getById(Long id);
	void create(VendorDto dto);
	void update(VendorDto dto);
	void delete(Long id);
	List<VendorDto> getAll();
}
