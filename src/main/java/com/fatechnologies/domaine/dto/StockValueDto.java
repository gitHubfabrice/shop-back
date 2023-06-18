package com.fatechnologies.domaine.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
public class StockValueDto {
    private Long id;
    private Instant createdAt;
    private double amount;
}
