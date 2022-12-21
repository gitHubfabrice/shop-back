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

public class VilleController {
	private Logger log = LoggerFactory.getLogger(VilleController.class);

	@Autowired
	private CityService villeService;

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody CityDto data) {
		 villeService.save(data);
	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody CityDto data) {
		villeService.save(data);
	}
	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CityDto>> getAllVille() {
		List<CityDto> dtos = villeService.getAll();
		return ResponseEntity.ok().body(dtos);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<CityDto> delete(@PathVariable("id") Long id) {
		villeService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
