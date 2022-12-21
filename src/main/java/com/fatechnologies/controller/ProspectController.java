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
	public ResponseEntity<ProspectDto> create(@RequestBody ProspectDto prospect) {

		ProspectDto prospecDto = prospectService.create(prospect);

		return ResponseEntity.ok().body(prospecDto);
	}
	
	@GetMapping(value = "/get-code", produces = MediaType.APPLICATION_JSON_VALUE)
	public int getCode() {
		int x = prospectService.nbre();

		if (x == 0) {
			return 0;
		}

		return prospectService.max();
	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProspectDto> update(@RequestBody ProspectDto prospect) {

		ProspectDto prospectDto = prospectService.update(prospect);

		return ResponseEntity.ok().body(prospectDto);
	}
	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProspectDto>> getAllProspect() {

		List<ProspectDto> prospects = prospectService.getAll();

		return ResponseEntity.ok().body(prospects);
	}
	
//	@GetMapping(value = "/search-code/{searchCodeStr}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity getProspect(@PathVariable("searchCodeStr")String searchCodeStr) {
//
//		ProspectDto prospect = prospectService.prospectByCode(searchCodeStr);
//
//		return ResponseEntity.ok().body(prospect);
//	}
//	
//	@PutMapping(value = "/create-client", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity createClient(@RequestBody ProspectDto prospect) {
//		
//		ProspectDto prospectDto = prospectService.createClient(prospect);
//		
//		return  ResponseEntity.ok().body(prospectDto);
//	}
	

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<ProspectDto> delete(@PathVariable("id") Long id) {

		prospectService.delete(id);

		return ResponseEntity.ok().build();
	}
	
}
