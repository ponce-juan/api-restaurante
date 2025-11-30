package com.restaurant.app.Product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Builder
public record ProductDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        int stock,
        String categoryName,
        String subcategoryName){}
