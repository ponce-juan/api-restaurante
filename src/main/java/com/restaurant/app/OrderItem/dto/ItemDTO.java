package com.restaurant.app.OrderItem.dto;

import java.math.BigDecimal;

public record ItemDTO(
        String name,
        int quantity,
        BigDecimal price
) {
}
