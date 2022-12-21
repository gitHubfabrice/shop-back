package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.ArticleDto;
import com.fatechnologies.domaine.dto.OrderDto;
import com.fatechnologies.domaine.entity.ArticleEntity;
import com.fatechnologies.domaine.entity.ArticleFourCommande;
import com.fatechnologies.domaine.entity.OrderVendor;
import com.fatechnologies.domaine.mapper.ArticleMapper;
import com.fatechnologies.domaine.mapper.OrderVendorMapper;
import com.fatechnologies.repository.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@Service
@Transactional
public class OrderVendorServiceImpl implements OrderVendorService {

	@Autowired
	private OrderVendorRepository orderRepository;

	@Autowired
	private OrderVendorMapper orderMapper;

	@Autowired
	private VendorRepository vendorRepository;
	
	@Autowired
	private ProspectRepository prospectRepository;

	
	private CommercialRepository commercialRepository;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public OrderDto getById(UUID id) {
		OrderVendor order = orderRepository.getById(id);
		OrderDto dto;

		dto = orderMapper.modelToDto(order);
		for (ArticleFourCommande ac : order.getArticleOrders()) {
			ArticleDto article = articleMapper.modelToDto(ac.getArticle());
			article.setQuantityArtOrd(ac.getQtyArtOrd());
			dto.getArticles().add(article);

		}

		return dto;
	}

	@Override
	public OrderDto create(OrderDto orderDto) {

		OrderVendor commande = orderMapper.dtoToModel(orderDto);

		double amount = 0;

		List<ArticleFourCommande> artCom = new ArrayList<>();
		for (ArticleDto art : orderDto.getArticles()) {
			Optional<ArticleEntity> articleOptional = this.articleRepository
					.findById(art.getId());

			if (articleOptional.isPresent() && commande != null) {
				ArticleFourCommande ac = new ArticleFourCommande();
				ac.setArticle(articleOptional.get());
				ac.setOrder(commande);
				ac.setQtyArtOrd(art.getQuantityArtOrd());
				ac.setPriceArtOrd(art.getPriceArtOrd());
				amount += art.getPriceArtOrd() * art.getQuantityArtOrd();
				artCom.add(ac);
			}
		}

		assert commande != null;
		commande.getArticleOrders().clear();
		commande.getArticleOrders().addAll(artCom);
		commande.setAmount(amount);
		commande.setVendor(vendorRepository.findByContact(orderDto.getVendorContact()));
		commande = orderRepository.saveAndFlush(commande);
		orderDto = orderMapper.modelToDto(commande);
		return orderDto;
	}

	@Override
	public OrderDto update(OrderDto orderDto) {

		OrderVendor order = orderMapper.dtoToModel(orderDto);
		var amount = 0;

		List<ArticleFourCommande> artCom = new ArrayList<>();
		for (ArticleDto art : orderDto.getArticles()) {
			Optional<ArticleEntity> articleOptional = this.articleRepository
					.findById(art.getId());

			if (articleOptional.isPresent() && order != null) {
				ArticleFourCommande ac = new ArticleFourCommande();
				ac.setArticle(articleOptional.get());
				ac.setOrder(order);
				ac.setQtyArtOrd(art.getQuantityArtOrd());
				ac.setPriceArtOrd(art.getPriceArtOrd());
				amount += art.getPriceArtOrd() * art.getQuantityArtOrd();
				artCom.add(ac);
			}
		}

		assert order != null;
		order.getArticleOrders().clear();
		order.getArticleOrders().addAll(artCom);
		order.setAmount(amount);
		order.setVendor(vendorRepository
				.findByContact(orderDto.getVendorContact()));
		order = orderRepository.saveAndFlush(order);
		orderDto = orderMapper.modelToDto(order);
		return orderDto;
	}

	@Override
	public void delete(UUID id) {
		orderRepository.deleteById(id);

	}

	@Override
	public List<OrderDto> getAll() {
		List<OrderVendor> orders = orderRepository.findAll();
		List<OrderDto> dtos = new ArrayList<>();

		for (OrderVendor co : orders) {
			OrderDto dto = orderMapper.modelToDto(co);
			for (ArticleFourCommande ac : co.getArticleOrders()) {
				ArticleDto art = articleMapper.modelToDto(ac.getArticle());
				art.setQuantityArtOrd(ac.getQtyArtOrd());
				art.setPriceArtOrd(ac.getPriceArtOrd());
				dto.getArticles().add(art);
			}
			dtos.add(dto);
		}
		return dtos;

	}

}
