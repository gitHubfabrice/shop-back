package com.fatechnologies.controller;

import com.fatechnologies.domaine.dto.CommercialDto;
import com.fatechnologies.service.CommercialService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shop/commercial")
@Setter
@Getter
@NoArgsConstructor
public class CommercialController {
	private Logger log = LoggerFactory.getLogger(CommercialController.class);

	@Autowired
	private CommercialService commercialService;

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommercialDto> create(@RequestBody CommercialDto commercial) {

		CommercialDto commercialDto = commercialService.create(commercial);

		return ResponseEntity.ok().body(commercialDto);
	}
	
	@GetMapping(value = "/get-code", produces = MediaType.APPLICATION_JSON_VALUE)
	public int getCode() {
		int x = commercialService.nbre();

		if (x == 0) {
			return 0;
		}

		return commercialService.max();
	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommercialDto> update(@RequestBody CommercialDto commercial) {

		CommercialDto commercialDto = commercialService.update(commercial);

		return ResponseEntity.ok().body(commercialDto);
	}

	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CommercialDto>> getAllCommercial() {

		List<CommercialDto> commercials = commercialService.getAll();

		return ResponseEntity.ok().body(commercials);
	}
	
	@GetMapping(value = "/connected/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommercialDto> getAllCommercialConnected(@PathVariable("login")String login) {

	    CommercialDto commercialDto = commercialService.connected(login);

		return ResponseEntity.ok().body(commercialDto);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<CommercialDto> delete(@PathVariable("id") Long id) {

		commercialService.delete(id);

		return ResponseEntity.ok().build();
	}

}
