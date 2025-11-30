package com.restaurant.app.Product.dto;

import lombok.Builder;

import java.math.BigDecimal;


@Builder
public record ProductDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        int stock,
        String category,
        String subCategory){}
