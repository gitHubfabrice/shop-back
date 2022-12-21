package com.fatechnologies.controller;

import com.fatechnologies.domaine.dto.CompteDto;
import com.fatechnologies.service.CompteService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@Setter
@RestController
@NoArgsConstructor
@RequestMapping("shop/compte")

public class CompteController {
	private Logger log = LoggerFactory.getLogger(CompteController.class);

	@Autowired
	private CompteService compteService;

	@GetMapping(value = "/get-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CompteDto> findBycommandeId(@PathVariable("id") Long id) {
		CompteDto compteDto = compteService.getById(id);

		if (compteDto == null) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body(compteDto);
		}

		return ResponseEntity.ok().body(compteDto);
	}


	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CompteDto> create(@RequestBody CompteDto compte) {

		CompteDto compteDto = compteService.create(compte);

		return ResponseEntity.ok().body(compteDto);
	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CompteDto> update(@RequestBody CompteDto compte) {

		CompteDto compteDto = compteService.update(compte);

		return ResponseEntity.ok().body(compteDto);
	}

	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CompteDto>> getAllcompte() {

		List<CompteDto> comptes = compteService.getAll();

		return ResponseEntity.ok().body(comptes);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<CompteDto> delete(@PathVariable("id") Long id) {

		compteService.delete(id);

		return ResponseEntity.ok().build();
	}

}
