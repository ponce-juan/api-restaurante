package com.restaurant.app.OrderItem.dto;

import java.math.BigDecimal;

public record OrderItemRequest(
        Long orderCustomerId,
        String productName,
        int quantity,
        BigDecimal price
) {
}
