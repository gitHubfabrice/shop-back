package com.fatechnologies.controller;

import com.fatechnologies.domaine.dto.CityDto;
import com.fatechnologies.service.CityService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shop/city")
@Getter
@Setter

public class CityController {
	private Logger log = LoggerFactory.getLogger(CityController.class);

	@Autowired
	private CityService villeService;

	@GetMapping(value = "/get-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CityDto> getAById(@PathVariable("id") long id) {
		var dto = villeService.getById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody CityDto data) {
		 villeService.save(data);
	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody CityDto data) {
		villeService.save(data);
	}
	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CityDto>> getAll() {
		var dtos = villeService.getAll();
		return ResponseEntity.ok().body(dtos);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<CityDto> delete(@PathVariable("id") Long id) {
		villeService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
