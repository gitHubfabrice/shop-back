                                                                                                                           package com.fatechnologies.service;

																														   import com.fatechnologies.domaine.dto.ArticleDto;
																														   import com.fatechnologies.domaine.dto.DeliveryDto;
																														   import com.fatechnologies.domaine.entity.Article;
																														   import com.fatechnologies.domaine.entity.ArticleReturn;
																														   import com.fatechnologies.domaine.entity.DeliveryReturn;
																														   import com.fatechnologies.domaine.mapper.ArticleMapper;
																														   import com.fatechnologies.domaine.mapper.DeliveryReturnMapper;
																														   import com.fatechnologies.repository.*;
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
public class DeliveryReturnServiceImpl implements DeliveryReturnService {

	@Autowired
	private DeliveryReturnRepository deliveryRepository;

	@Autowired
	private DeliveryReturnMapper deliveryMapper;

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	

	@Autowired
	private CommissionRepository commissionRepository;

	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private ProspectRepository prospectRepository;



	@Override
	public DeliveryDto getById(UUID id) {
		Optional<DeliveryReturn> delivery = deliveryRepository.findById(id);

		DeliveryDto dto = null;
		if (delivery.isPresent()) {
			dto = deliveryMapper.modelToDto(delivery.get());
		}

		return dto;
	}

	@Override
	public void create(DeliveryDto deliveryDto) {

		var delivery = deliveryMapper.dtoToModel(deliveryDto);

		List<ArticleReturn> artLiv = new ArrayList<>();
		for (ArticleDto art : deliveryDto.getArticles()) {
			Optional<Article> articleOptional = this.articleRepository.findById(art.getId());
			if (articleOptional.isPresent()) {

				ArticleReturn al = new ArticleReturn();
				al.setArticle(articleOptional.get());
				al.setDeliveryReturn(delivery);
				al.setQtyReturn(art.getQuantityArtDel());
			
					
					// faire le stockage
					var atQ = articleOptional.get().getQuantity() + art.getQuantityArtDel();
					art.setQuantity(atQ);
					art.setCategoryId(articleOptional.get().getCategory().getId());
					Article article = this.articleMapper.dtoToModel(art);
					article.setCategory(categoryRepository.findById(art.getCategoryId()).orElseThrow(BasicException::new));
					this.articleRepository.saveAndFlush(article);
					artLiv.add(al);			

			}
		}

		delivery.setClient(prospectRepository.findByContact(deliveryDto.getClientContact()));
		delivery.getArticleReturns().clear();
		delivery.getArticleReturns().addAll(artLiv);
		deliveryRepository.saveAndFlush(delivery);

	}

	@Override
	public void update(DeliveryDto deliveryDto) {

		var  delivery = deliveryMapper.dtoToModel(deliveryDto);
		var artLiv = new ArrayList<ArticleReturn>();
		for (ArticleDto art : deliveryDto.getArticles()) {
			Optional<Article> articleOptional = this.articleRepository.findById(art.getId());
			if (articleOptional.isPresent()) {

				ArticleReturn al = new ArticleReturn();
				al.setArticle(articleOptional.get());
				al.setDeliveryReturn(delivery);
				al.setQtyReturn(art.getQuantityArtDel());
				
				//restitution de la valeur
				art.less(art.getQuantityTemp());
				art.more(articleOptional.get().getQuantity());

				

				art.setCategoryId(articleOptional.get().getCategory().getId());
				//ajouter au stock
				art.more(art.getQuantityArtDel());
				Article article1 = this.articleMapper.dtoToModel(art);
				this.articleRepository.saveAndFlush(article1);

				artLiv.add(al);
			}
		}

		delivery.getArticleReturns().clear();
		delivery.getArticleReturns().addAll(artLiv);
		delivery.setClient(prospectRepository.findByContact(deliveryDto.getClientContact()));
		deliveryRepository.saveAndFlush(delivery);

	}

	@Override
	public void delete(UUID id) {
		deliveryRepository.deleteById(id);

	}

	@Override
	public List<DeliveryDto> getAll() {

		List<DeliveryReturn> deliveries = deliveryRepository.findAll();
		List<DeliveryDto> dtos = new ArrayList<>();
		for (DeliveryReturn li : deliveries) {
			var dto = deliveryMapper.modelToDto(li);
			for (ArticleReturn al : li.getArticleReturns()) {
				ArticleDto art = articleMapper.modelToDto(al.getArticle());
				art.setQuantityArtDel(al.getQtyReturn());
				art.setQuantityTemp(al.getQtyReturn());
				dto.getArticles().add(art);
				
			}
			dtos.add(dto);

		}
		return dtos;

	}

}
