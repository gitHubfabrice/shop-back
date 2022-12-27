package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.CityDto;
import com.fatechnologies.domaine.entity.CityEntity;
import com.fatechnologies.domaine.mapper.CityMapper;
import com.fatechnologies.repository.CityRepository;
import com.fatechnologies.security.exception.BasicException;
import com.fatechnologies.security.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
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
		city.setLabel(Constants.toUpperCase(city.getLabel()));
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
		for (CityEntity e : cities) {
			dtos.add(cityMapper.modelToDto(e));
		}
		dtos.sort(Comparator.comparing(CityDto::getLabel));
		return dtos;

	}


}
