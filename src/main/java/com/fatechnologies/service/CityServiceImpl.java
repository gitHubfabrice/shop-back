package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.CityDto;
import com.fatechnologies.domaine.entity.City;
import com.fatechnologies.domaine.mapper.CityMapper;
import com.fatechnologies.repository.CityRepository;
import com.fatechnologies.security.exception.BasicException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Service
@Transactional
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private CityMapper cityMapper;


	@Override
	public CityDto getById(Long id) {
		var city = cityRepository.findById(id).orElseThrow(BasicException::new);
		return cityMapper.modelToDto(city);
	}

	@Override
	public void save(CityDto dto) {
		var city = cityMapper.dtoToModel(dto);
		cityRepository.saveAndFlush(city);
	}


	@Override
	public void delete(Long id) {
		cityRepository.deleteById(id);

	}

	@Override
	public List<CityDto> getAll() {
		var cities = cityRepository.findAll();
		List<CityDto> dtos = new ArrayList<>();
		for (City e : cities) {
			dtos.add(cityMapper.modelToDto(e));
		}
		return dtos;

	}


}
