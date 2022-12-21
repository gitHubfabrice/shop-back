package com.fatechnologies.controller;

import com.fatechnologies.domaine.dto.CategoryDto;
import com.fatechnologies.service.CategoryService;
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

@Getter
@Setter
@RestController
@NoArgsConstructor
@RequestMapping("shop/category")

public class CategoryController {
	private Logger log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;

	@GetMapping(value = "/get-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoryDto> findById(@PathVariable("id") int id) {
		var categoryDto = categoryService.getById(id);
		return ResponseEntity.ok().body(categoryDto);
	}

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody CategoryDto category) {
		categoryService.save(category);
	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody CategoryDto category) {
		categoryService.save(category);

	}

	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoryDto>> getAll() {

		List<CategoryDto> categorys = categoryService.getAll();

		return ResponseEntity.ok().body(categorys);
	}

	@DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable("id") int id) {
		categoryService.delete(id);
	}

}
