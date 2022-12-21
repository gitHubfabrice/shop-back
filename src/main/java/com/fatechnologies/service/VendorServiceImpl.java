package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.VendorDto;
import com.fatechnologies.domaine.entity.Vendor;
import com.fatechnologies.domaine.mapper.ProspectMapper;
import com.fatechnologies.domaine.mapper.VendorMapper;
import com.fatechnologies.repository.ProspectRepository;
import com.fatechnologies.repository.VendorRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Setter
@Getter
@Service
@Transactional
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorRepository vendorRepository;
	
	@Autowired
	private VendorMapper vendorMapper;
	
	@Autowired
	private ProspectRepository prospectRepository;
	
	@Autowired
	private ProspectMapper prospectMapper;
	
	@Override
	public VendorDto getById(Long id) {
		Optional<Vendor> fournisseur = vendorRepository.findById(id);
		
		VendorDto dto = null;
		if(fournisseur != null && fournisseur.isPresent()) {
			dto = vendorMapper.modelToDto(fournisseur.get());
		}
		
		return dto;
	}

	@Override
	public void create(VendorDto vendorDto) {
		Vendor vendor = vendorMapper.dtoToModel(vendorDto);
		vendorRepository.saveAndFlush(vendor);
	}

	@Override
	public void update(VendorDto vendorDto) {
		Vendor vendor = vendorMapper.dtoToModel(vendorDto);
		vendorRepository.saveAndFlush(vendor);
	}

	@Override
	public void delete(Long id) {
		vendorRepository.deleteById(id);
	}

	@Override
	public List<VendorDto> getAll() {
		List<Vendor> vendors = vendorRepository.findAll();
		List<VendorDto> dtos = new ArrayList<>();
		for(Vendor fo : vendors) {
			dtos.add(vendorMapper.modelToDto(fo));
		}
		return dtos;
		
	}
}
