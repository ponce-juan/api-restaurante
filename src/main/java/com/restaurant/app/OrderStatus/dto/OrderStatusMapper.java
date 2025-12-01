package com.restaurant.app.OrderStatus.dto;

import com.restaurant.app.OrderStatus.entity.OrderStatus;

public class OrderStatusMapper {
    public static OrderStatusDTO toDTO(OrderStatus entity){
        return new OrderStatusDTO(
                entity.getId(),
                entity.getStatus().name()
        );
    }
}
