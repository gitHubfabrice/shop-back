package com.fatechnologies.controller;

import com.fatechnologies.command.ArticleCommand;
import com.fatechnologies.domaine.dto.ArticleDto;
import com.fatechnologies.interactor.ArticleInteract;
import com.fatechnologies.service.ArticleService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shop/article")
public class ArticleController {
	private final ArticleService articleService;
	private final ArticleInteract articleInteract;

	public ArticleController(ArticleService articleService,
							 ArticleInteract articleInteract) {
		this.articleService = articleService;
		this.articleInteract = articleInteract;
	}

	@GetMapping(value = "/get-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArticleDto> findById(@PathVariable("id") int id) {
		var articleDto = articleService.getById(id);
		return ResponseEntity.ok().body(articleDto);
	}

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void create(@RequestBody ArticleCommand command) {
		 articleInteract.create(command);
	}

	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@RequestBody ArticleCommand command) {
		articleInteract.update(command);
	}

	@GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ArticleDto>> getAllArticle() {
		List<ArticleDto> articles = articleService.getAll();
		return ResponseEntity.ok().body(articles);
	}

	@GetMapping(value = "/get-stock-value", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Double> getStockValue() {
		var amount = articleService.getStockValue();
		return ResponseEntity.ok().body(amount);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<ArticleDto> delete(@PathVariable("id") int id) {
		articleService.delete(id);
		return ResponseEntity.ok().build();
	}

}
