package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.ArticleDto;
import com.fatechnologies.domaine.dto.FileDto;
import com.fatechnologies.domaine.entity.ArticleEntity;
import com.fatechnologies.domaine.entity.FileEntity;
import com.fatechnologies.domaine.mapper.ArticleMapper;
import com.fatechnologies.domaine.mapper.FileMapper;
import com.fatechnologies.repository.ArticleRepository;
import com.fatechnologies.repository.FileRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
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
	@Autowired
	private FileService fileService;
	@Autowired
	private FileMapper fileMapper;

	@Override
	public ArticleDto getById(int id) {
		var article = articleRepository.findById(id).orElseThrow();
		var files = new HashSet<FileDto>();
		for (FileEntity file : article.getFiles()) {
			var dto = fileMapper.modelToDto(file);
			dto.setFile(fileService.loadImage(dto.getFilename(),dto.getType(), dto.getUrl()));
			files.add(dto);
		}
		var articleDto = articleMapper.modelToDto(article);
		articleDto.getFiles().clear();
		articleDto.getFiles().addAll(files);
		return articleDto;
	}

	@Override
	public void save(ArticleDto dto) {
		var article = articleMapper.dtoToModel(dto);
		for (FileEntity file : article.getFiles()) {
			file.setArticle(article);
		}
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
	@Override
	public int idGen(){
		var nbre = articleRepository.nbre();
		if (nbre == 0)
			return 1;
		else return articleRepository.max() + 1;
	}

}
