package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.ArticleDto;
import com.fatechnologies.domaine.dto.OrderDto;
import com.fatechnologies.domaine.entity.ArticleEntity;
import com.fatechnologies.domaine.entity.ArticleOrder;
import com.fatechnologies.domaine.entity.Order;
import com.fatechnologies.domaine.mapper.ArticleMapper;
import com.fatechnologies.domaine.mapper.OrderMapper;
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
public class OrderServiceImpl implements OrderService {


	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private VendorRepository vendorRepository;

	@Autowired
	private ProspectRepository prospectRepository;

	@Autowired
	private CommercialRepository commercialRepository;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public OrderDto getById(UUID id) {
		var commande = orderRepository.getById(id);
		var dto = orderMapper.modelToDto(commande);
		for (ArticleOrder ac : commande.getArticleOrders()) {
			ArticleDto article = articleMapper.modelToDto(ac.getArticle());
			article.setQuantityArtOrd(ac.getQuantityArtCom());
			dto.getArticles().add(article);
		}

		return dto;
	}

	@Override
	public OrderDto getByReference(String referenceCommande) {

		var commande = orderRepository.findByCode(referenceCommande);
		return orderMapper.modelToDto(commande);
	}

	@Override
	public void create(OrderDto orderDto) {

		var order = orderMapper.dtoToModel(orderDto);
		var prospect = prospectRepository.findByContact(orderDto.getClientContact());
		if (prospect != null) {

			var amount = 0;

			List<ArticleOrder> artCom = new ArrayList<>();
			for (ArticleDto art : orderDto.getArticles()) {
				Optional<ArticleEntity> articleOptional = this.articleRepository.findById(art.getId());

				if (articleOptional.isPresent() && order != null) {
					ArticleOrder ac = new ArticleOrder();
					ac.setArticle(articleOptional.get());
					ac.setOrder(order);
					ac.setQuantityArtCom(art.getQuantityArtOrd());
					ac.setQuantityArtLiv(0);
					ac.setPriceArtCom(art.getPriceArtOrd());
					amount += art.getPriceArtOrd() * art.getQuantityArtOrd();
					artCom.add(ac);
				}
			}

			order.getArticleOrders().clear();
			order.getArticleOrders().addAll(artCom);
			order.setClient(prospect);
			order.setAmount(amount);
			order = orderRepository.saveAndFlush(order);
			orderDto = orderMapper.modelToDto(order);

		} else {
			orderDto.setMessage("Le client n'existe pas dans la base de donnée");
		}

	}

	@Override
	public void update(OrderDto orderDto) {
		var order = orderMapper.dtoToModel(orderDto);
		var amount = 0;

		List<ArticleOrder> artCom = new ArrayList<>();
		for (ArticleDto art : orderDto.getArticles()) {
			Optional<ArticleEntity> articleOptional = this.articleRepository.findById(art.getId());

			if (articleOptional.isPresent() && order != null) {
				ArticleOrder ac = new ArticleOrder();
				ac.setArticle(articleOptional.get());
				ac.setOrder(order);
				ac.setQuantityArtCom(art.getQuantityArtOrd());
				ac.setQuantityArtLiv(art.getQuantityArtDel());
				ac.setPriceArtCom(art.getPriceArtOrd());
				amount += art.getPriceArtOrd() * art.getQuantityArtOrd();
				artCom.add(ac);
			}
		}

		order.getArticleOrders().clear();
		order.getArticleOrders().addAll(artCom);
		order.setAmount(amount);
		order = orderRepository.saveAndFlush(order);
		orderDto = orderMapper.modelToDto(order);
	}

	@Override
	public void delete(UUID id) {
		orderRepository.deleteById(id);

	}

	@Override
	public List<OrderDto> getAll() {
		List<Order> orders = orderRepository.findAll();
		List<OrderDto> dtos = new ArrayList<>();

		for (Order co : orders) {
			OrderDto dto = orderMapper.modelToDto(co);
			for (ArticleOrder ac : co.getArticleOrders()) {
				ArticleDto art = articleMapper.modelToDto(ac.getArticle());
				art.setQuantityArtOrd(ac.getQuantityArtCom());
				art.setPriceArtOrd(ac.getPriceArtCom());
				art.setQuantityArtDel(ac.getQuantityArtLiv());
				dto.getArticles().add(art);
			}
			dtos.add(dto);
		}
		return dtos;

	}


	@Override
	public List<OrderDto> getByStatus(String status) {
		List<Order> orders = orderRepository.findByStatus(status);
		List<OrderDto> dtos = new ArrayList<>();

		for (Order co : orders) {
			OrderDto dto = orderMapper.modelToDto(co);
			for (ArticleOrder ac : co.getArticleOrders()) {
				ArticleDto art = articleMapper.modelToDto(ac.getArticle());
				art.setQuantityArtOrd(ac.getQuantityArtCom());
				art.setPriceArtOrd(ac.getPriceArtCom());
				art.setQuantityArtDel(ac.getQuantityArtLiv());
				dto.getArticles().add(art);
			}
			dtos.add(dto);
		}
		return dtos;
	}

}
