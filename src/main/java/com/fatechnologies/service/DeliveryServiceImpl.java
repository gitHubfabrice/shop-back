package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.ArticleDto;
import com.fatechnologies.domaine.dto.DeliveryDto;
import com.fatechnologies.domaine.entity.Article;
import com.fatechnologies.domaine.entity.ArticleDelivery;
import com.fatechnologies.domaine.entity.Delivery;
import com.fatechnologies.domaine.entity.Order;
import com.fatechnologies.domaine.mapper.ArticleMapper;
import com.fatechnologies.domaine.mapper.DeliveryMapper;
import com.fatechnologies.repository.ArticleRepository;
import com.fatechnologies.repository.CategoryRepository;
import com.fatechnologies.repository.DeliveryRepository;
import com.fatechnologies.repository.OrderRepository;
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
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Autowired
	private DeliveryMapper deliveryMapper;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ArticleMapper articleMapper;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public DeliveryDto getById(UUID id) {
		Optional<Delivery> livraison = deliveryRepository.findById(id);

		DeliveryDto dto = null;
		if (livraison.isPresent()) {
			dto = deliveryMapper.modelToDto(livraison.get());
		}

		return dto;
	}

	@Override
	public void create(DeliveryDto deliveryDto) {
		
		double montant = 0;
		String message = null;

		List<ArticleDelivery> artLiv = new ArrayList<>();
		var delivery = deliveryMapper.dtoToModel(deliveryDto);

		for (ArticleDto art : deliveryDto.getArticles()) {
			Optional<Article> articleOptional = this.articleRepository.findById(art.getId());
			if(articleOptional.isPresent()){
			//verifions si l'article est disponible en stock
			if (art.getQuantity() > art.getQuantityArtDel()) {

				ArticleDelivery al = new ArticleDelivery();
				al.setArticle(articleOptional.get());
				al.setDelivery(delivery);
				al.setQuantityArtLiv(art.getQuantityArtDel());
				al.setPriceArtDel(art.getPriceArtDel());
				montant += art.getPriceArtDel() * art.getQuantityArtDel();

				// mise à jour du stock
				Article article = this.articleMapper.dtoToModel(art);
				article.setQuantity(article.getQuantity() - art.getQuantityArtDel());
				articleRepository.saveAndFlush(article);
				artLiv.add(al);
			}else {
				message = "La quantité à livé n'est pas disponible";
			}

			}
		}

		if (message != null) {
			deliveryDto = new DeliveryDto();
			deliveryDto.setMessage(message);
		}


		delivery.getArticleDeliveries().clear();
		delivery.getArticleDeliveries().addAll(artLiv);
		delivery.setAmount(montant);
		delivery = deliveryRepository.saveAndFlush(delivery);
	}

	@Override
	public void update(DeliveryDto deliveryDto) {

		Optional<Article> articleOptional;
		var amount = 0;
		var  artLiv = new ArrayList<ArticleDelivery>();
		String message = null;
		var memoire = 0;
		Order order = new Order();
		Delivery delivery = deliveryMapper.dtoToModel(deliveryDto);


			for (ArticleDto art : deliveryDto.getArticles()) {
					articleOptional = this.articleRepository.findById(art.getId());
				if (articleOptional.isPresent()) {
					//verification de la disponibilé de l'article
					if (art.getQuantity() + art.getQuantityTemp() > art.getQuantityArtDel()) {
						memoire = art.getQuantityTemp();
						ArticleDelivery al = new ArticleDelivery();
						al.setArticle(articleOptional.get());
						al.setDelivery(delivery);
						al.setQuantityArtLiv(art.getQuantityArtDel());
						al.setPriceArtDel(art.getQuantityArtDel());
						amount += art.getPriceArtDel() * art.getQuantityArtDel();
						artLiv.add(al);
						Article article = this.articleMapper.dtoToModel(art);
						article.setQuantity(article.getQuantity() + memoire - art.getQuantityArtDel());
						articleRepository.saveAndFlush(article);
						order.setStatus("en-cours");
					}

					else {
						message = "La quantité à livé n'est pas disponible";
					}

				}
			}


		if (message != null) {
			deliveryDto = new DeliveryDto();
			deliveryDto.setMessage(message);
		}


		delivery.getArticleDeliveries().clear();
		delivery.getArticleDeliveries().addAll(artLiv);
		delivery.setAmount(amount);

		delivery = deliveryRepository.saveAndFlush(delivery);
	}

	@Override
	public void delete(UUID id) {
		deliveryRepository.deleteById(id);
	}

	@Override
	public List<DeliveryDto> getAll() {

		List<Delivery> deliveries = deliveryRepository.findAll();
		List<DeliveryDto> dtos = new ArrayList<>();
		for (Delivery li : deliveries) {
			DeliveryDto dto = deliveryMapper.modelToDto(li);
			for (ArticleDelivery al : li.getArticleDeliveries()) {
				ArticleDto art = articleMapper.modelToDto(al.getArticle());
				art.setQuantityTemp(al.getQuantityArtLiv());
				art.setQuantityArtDel(al.getQuantityArtLiv());
				art.setPriceArtDel(al.getPriceArtDel());
				dto.getArticles().add(art);

			}
			dtos.add(dto);

		}
		return dtos;

	}

}
