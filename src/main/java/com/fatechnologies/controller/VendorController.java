package com.fatechnologies.controller;

import com.fatechnologies.domaine.dto.VendorDto;
import com.fatechnologies.service.VendorService;
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
@RequestMapping("shop/vendor")
@Getter
@Setter
public class VendorController {
	private Logger log = LoggerFactory.getLogger(VendorController.class);

	@Autowired
	private VendorService vendorService;

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody VendorDto dto) {
		 vendorService.create(dto);
	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody VendorDto dto) {
		vendorService.update(dto);
	}

	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VendorDto>> getAll() {

		List<VendorDto> fournisseurs = vendorService.getAll();

		return ResponseEntity.ok().body(fournisseurs);
	}
	
	
	@DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable("id") Long id) {
		vendorService.delete(id);
	}

}
