package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.ArticleDto;
import com.fatechnologies.domaine.entity.ArticleEntity;
import com.fatechnologies.domaine.mapper.ArticleMapper;
import com.fatechnologies.repository.ArticleRepository;
import com.fatechnologies.repository.FileRepository;
import com.fatechnologies.security.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private FileRepository fileRepository;

	@Override
	public ArticleDto getById(int id) {
		var article = articleRepository.findById(id).orElseThrow();
		return articleMapper.modelToDto(article);
	}

	@Override
	public void create(ArticleDto dto) {
		var article = articleMapper.dtoToModel(dto);
		article.setReference("ART-ELED000" + idGen());
		article.setLabel(Constants.toUpperCase(article.getLabel()));
		article.setCreatedAt(LocalDateTime.now());
		articleRepository.saveAndFlush(article);
	}

	@Override
	public void update(ArticleDto dto) {
		var article = articleMapper.dtoToModel(dto);
		article.setLabel(Constants.toUpperCase(article.getLabel()));
		articleRepository.saveAndFlush(article);
	}

	@Override
	public void delete(int id) {
		articleRepository.deleteById(id);
	}

	@Override
	public List<ArticleDto> getAll() {
		List<ArticleEntity> articleEntities = articleRepository.findAll();
		List<ArticleDto> dtos = new ArrayList<>();
		for (var article : articleEntities) {
			ArticleDto dto = articleMapper.modelToDto(article);
			dtos.add(dto);
		}
		dtos.sort(Comparator.comparing(ArticleDto::getLabel));
		return dtos;

	}

	@Override
	public double getStockValue() {
		List<ArticleEntity> articleEntities = articleRepository.findAll();
		var amount = 0;
		for (var article : articleEntities) {
			amount += article.getQuantity() * article.getPrice();
		}
		return amount;
	}

	public int idGen(){
		var nbre = articleRepository.nbre();
		if (nbre == 0)
			return 1;
		else return articleRepository.max() + 1;
	}

}
