package com.restaurant.app.OrderCustomer.dto;

import com.restaurant.app.OrderItem.dto.ItemDTO;
import com.restaurant.app.OrderType.dto.OrderTypeDTO;

import java.math.BigDecimal;
import java.util.List;

public record OrderCustomerRequest(
        Long id,
        String client,
        OrderTypeDTO type,
        List<ItemDTO> items,
        String status,
        BigDecimal total
) { }
