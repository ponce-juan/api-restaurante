package com.restaurant.app.OrderCustomer.dto;

import com.restaurant.app.OrderItem.dto.ItemDTO;
import com.restaurant.app.OrderStatus.dto.OrderStatusDTO;
import com.restaurant.app.OrderType.dto.OrderTypeDTO;

import java.math.BigDecimal;
import java.util.List;

public record OrderCustomerResponse(
        Long id,
//        String client,
        OrderTypeDTO type,
        OrderStatusDTO status,
        BigDecimal total,
        List<ItemDTO> items
) { }
