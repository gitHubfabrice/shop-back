package com.fatechnologies.service;

import com.fatechnologies.domaine.dto.ArticleDto;
import com.fatechnologies.domaine.dto.OrderDto;
import com.fatechnologies.domaine.entity.ArticleOrder;
import com.fatechnologies.domaine.entity.OrderEntity;
import com.fatechnologies.domaine.mapper.ArticleMapper;
import com.fatechnologies.domaine.mapper.OrderMapper;
import com.fatechnologies.repository.ArticleRepository;
import com.fatechnologies.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author ASSAGOU FABRICE 15/04/2023
 */
@Service
public class OrderServiceImpl implements OrderService{

  private final OrderRepository orderRepository;
  private final ArticleRepository articleRepository;
  private final OrderMapper orderMapper;
  private final ArticleMapper articleMapper;
  private final ArticleService articleService;

  public OrderServiceImpl(OrderRepository orderRepository,
                          ArticleRepository articleRepository, ArticleService articleService) {
    this.orderRepository = orderRepository;
    this.articleRepository = articleRepository;
    this.articleService = articleService;
    orderMapper = OrderMapper.INSTANCE;
    articleMapper = ArticleMapper.INSTANCE;
  }

  @Override
  public OrderDto findById(Long id) {
    var order = orderRepository.findById(id).orElseThrow();
    var dto = orderMapper.modelToDto(order);
    getAllArticle(order, dto);
    return dto;
  }

  @Override
  public void save(OrderDto dto) {
    var artLiv = new ArrayList<ArticleOrder>();
    var order = orderMapper.dtoToModel(dto);
    extracted(dto, artLiv, order);
    order.getArticles().clear();
    order.getArticles().addAll(artLiv);
    orderRepository.save(order);
  }

  private void extracted(OrderDto dto, ArrayList<ArticleOrder> artLiv, OrderEntity order) {
    if(!dto.getArticles().isEmpty()){
      for (ArticleDto article : dto.getArticles()) {
        var art = this.articleRepository.findById(article.getId()).orElseThrow();
        ArticleOrder ao = new ArticleOrder();
        ao.setArticle(art);
        ao.setOrder(order);
        ao.setQuantity(article.getQuantityArtOrd());
        ao.setPrice(article.getPriceArtOrd());
        articleService.updateInventory("out", article.getId(),article.getQuantityArtOrd(), article.getQuantityArtOrd());
        artLiv.add(ao);
      }
    }
  }

  @Override
  public void delete(Long id) {
    orderRepository.deleteById(id);
  }

  @Override
  public List<OrderDto> getAllOrder() {
    var orders = orderRepository.findAll();
    return getArticles(orders);
  }

  @NotNull
  private List<OrderDto> getArticles(List<OrderEntity> orders) {
    var dtos = new ArrayList<OrderDto>();
    for (var o : orders) {
      var dto = orderMapper.modelToDto(o);
      dto.setAmount(dto.getAmount());
      getAllArticle(o, dto);
      dtos.add(dto);
    }
    dtos.sort(Comparator.comparing(OrderDto::getCreatedAt).reversed());
    return dtos;
  }

  private void getAllArticle(OrderEntity order, OrderDto dto) {
    for (ArticleOrder ao : order.getArticles()) {

      var art = articleRepository.findById(ao.getArticle().getId()).orElseThrow();
      var artDto = articleMapper.modelToDto(art);
      artDto.setQuantityTemp(ao.getQuantity());
      artDto.setQuantityArtOrd(ao.getQuantity());
      artDto.setPriceArtOrd(ao.getPrice());
      dto.getArticles().add(artDto);
    }
  }
}
