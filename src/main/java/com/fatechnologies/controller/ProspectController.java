package com.fatechnologies.controller;

import com.fatechnologies.domaine.dto.ProspectDto;
import com.fatechnologies.service.ProspectService;
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
@RequestMapping("shop/prospect")
@Getter
@Setter

public class ProspectController {
	private Logger log = LoggerFactory.getLogger(ProspectController.class);

	@Autowired
	private ProspectService prospectService;

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody ProspectDto prospect) {
		 prospectService.save(prospect);
	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody ProspectDto prospect) {
		prospectService.save(prospect);
	}
	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProspectDto>> getAllProspect() {

		List<ProspectDto> prospects = prospectService.getAll();

		return ResponseEntity.ok().body(prospects);
	}


	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<ProspectDto> delete(@PathVariable("id") Long id) {

		prospectService.delete(id);

		return ResponseEntity.ok().build();
	}
	
}
