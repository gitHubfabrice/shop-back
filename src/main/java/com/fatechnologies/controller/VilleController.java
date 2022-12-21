package com.fatechnologies.controller;

import com.fatechnologies.domaine.dto.VilleDto;
import com.fatechnologies.service.VilleService;
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
@RequestMapping("shop/ville")
@Getter
@Setter

public class VilleController {
	private Logger log = LoggerFactory.getLogger(VilleController.class);

	@Autowired
	private VilleService villeService;

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VilleDto> create(@RequestBody VilleDto data) {

		VilleDto dto = villeService.create(data);

		return ResponseEntity.ok().body(dto);
	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<VilleDto> update(@RequestBody VilleDto data) {

		VilleDto dto = villeService.update(data);

		return ResponseEntity.ok().body(dto);
	}
	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VilleDto>> getAllVille() {

		List<VilleDto> dtos = villeService.getAll();

		return ResponseEntity.ok().body(dtos);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<VilleDto> delete(@PathVariable("id") Long id) {

		villeService.delete(id);

		return ResponseEntity.ok().build();
	}
	
}
