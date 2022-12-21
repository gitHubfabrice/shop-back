package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.ArticleDto;
import com.fatechnologies.domaine.dto.DeliveryDto;
import com.fatechnologies.domaine.entity.Article;
import com.fatechnologies.domaine.entity.ArticleVendor;
import com.fatechnologies.domaine.entity.DeliveryVendor;
import com.fatechnologies.domaine.mapper.ArticleMapper;
import com.fatechnologies.domaine.mapper.DeliveryVendorMapper;
import com.fatechnologies.repository.ArticleRepository;
import com.fatechnologies.repository.CategoryRepository;
import com.fatechnologies.repository.DeliveryVendorRepository;
import com.fatechnologies.repository.VendorRepository;
import com.fatechnologies.security.exception.BasicException;
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
public class DeliveryVendorServiceImpl implements DeliveryVendorService {

	@Autowired
	private DeliveryVendorRepository deliveryRepository;
	@Autowired
	private DeliveryVendorMapper deliveryMapper;
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private VendorRepository vendorRepository;
	@Autowired
	private ArticleMapper articleMapper;

	@Override
	public DeliveryDto getById(UUID id) {
		var delivery = deliveryRepository.findById(id).orElseThrow(BasicException::new);
		return deliveryMapper.modelToDto(delivery);
	}

	@Override
	public void create(DeliveryDto deliveryDto) {

		var delivery = deliveryMapper.dtoToModel(deliveryDto);
		List<ArticleVendor> artLiv = new ArrayList<>();
		var amount = 0;

		for (ArticleDto art : deliveryDto.getArticles()) {
			var articleOptional = this.articleRepository.findById(art.getId());
			if (articleOptional.isPresent()) {

				ArticleVendor al = new ArticleVendor();
				al.setArticle(articleOptional.get());
				al.setDeliveryVendor(delivery);
				al.setQtyArtLiv(art.getQuantityArtDel());
				al.setPriceArtLiv(art.getPriceArtDel());

				//ajouter dans  le stock
				var atQ = articleOptional.get().getQuantity() + art.getQuantityArtDel();
				art.setQuantity(atQ);
				art.setCategoryId(articleOptional.get().getCategory().getId());
				Article article = this.articleMapper.dtoToModel(art);
				this.articleRepository.saveAndFlush(article);
				amount += art.getPriceArtDel() * art.getQuantityArtDel();
				artLiv.add(al);

			}
		}

		delivery.setVendor(vendorRepository.findByContact(deliveryDto.getVendorContact()));
		delivery.getArticleVendors().clear();
		delivery.getArticleVendors().addAll(artLiv);
		delivery.setAmount(amount);
		deliveryRepository.saveAndFlush(delivery);

	}

	@Override
	public void update(DeliveryDto deliveryDto) {

		DeliveryVendor delivery = deliveryMapper.dtoToModel(deliveryDto);
		var amount = 0;
		List<ArticleVendor> artLiv = new ArrayList<>();
		for (ArticleDto art : deliveryDto.getArticles()) {
			Optional<Article> articleOptional = this.articleRepository.findById(art.getId());
			if (articleOptional.isPresent()) {
				ArticleVendor al = new ArticleVendor();
				al.setArticle(articleOptional.get());
				al.setDeliveryVendor(delivery);
				al.setQtyArtLiv(art.getQuantityArtDel());
				al.setPriceArtLiv(art.getPriceArtDel());

				//restitution de la valeur
				art.less(art.getQuantity());
				art.more(articleOptional.get().getQuantity());
				art.setCategoryId(articleOptional.get().getCategory().getId());
				
				var article1 = this.articleMapper.dtoToModel(art);

				article1.setCategory(categoryRepository.findById(art.getCategoryId()).orElseThrow(BasicException::new));
				article1 = this.articleRepository.saveAndFlush(article1);
				
				//ajouter dans le stock
				art.less(art.getQuantityArtDel());
				article1.setQuantity(art.getQuantity());
				this.articleRepository.saveAndFlush(article1);
				amount += art.getPriceArtDel() * art.getQuantityArtDel();

				artLiv.add(al);
			}
		}

		delivery.getArticleVendors().clear();
		delivery.getArticleVendors().addAll(artLiv);
		delivery.setAmount(amount);
		delivery.setVendor(vendorRepository.findByContact(deliveryDto.getVendorContact()));
		deliveryRepository.saveAndFlush(delivery);

	}

	@Override
	public void delete(UUID id) {
		deliveryRepository.deleteById(id);

	}

	@Override
	public List<DeliveryDto> getAll() {

		List<DeliveryVendor> deliveries = deliveryRepository.findAll();
		List<DeliveryDto> dtos = new ArrayList<>();
		for (DeliveryVendor li : deliveries) {
			DeliveryDto dto = deliveryMapper.modelToDto(li);
			for (ArticleVendor al : li.getArticleVendors()) {
				ArticleDto art = articleMapper.modelToDto(al.getArticle());
				art.setQuantityArtDel(al.getQtyArtLiv());
				art.setPriceArtDel(al.getPriceArtLiv());
				art.setQuantityTemp(al.getQtyArtLiv());
				dto.getArticles().add(art);
				
			}
			dtos.add(dto);

		}
		return dtos;

	}

}
