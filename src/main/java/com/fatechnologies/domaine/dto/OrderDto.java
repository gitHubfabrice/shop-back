package com.fatechnologies.domaine.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author ASSAGOU FABRICE 15/04/2023
 */
@Getter
@Setter
public class OrderDto {
  private Long id;
  private Integer reference;
  private boolean status;
  private double amount;
  private LocalDate date;
  private Long clientId;
  private String clientLastname;
  private String clientFirstname;
  private String clientCityLabel;
  private UUID userId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime validatedAt;
  private List<ArticleDto> articles = new ArrayList<>();

  public double getAmount(List<ArticleDto> articles) {
    for (ArticleDto article : articles) {
      this.amount += article.getQuantityArtOrd() * article.getPriceArtOrd();
    }
    return this.amount;
  }
}
