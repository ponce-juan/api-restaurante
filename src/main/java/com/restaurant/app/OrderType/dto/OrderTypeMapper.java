package com.restaurant.app.OrderType.dto;

import com.restaurant.app.OrderType.entity.OrderType;

public class OrderTypeMapper {
    public static OrderTypeDTO toDTO(OrderType entity){
        return new OrderTypeDTO(
                entity.getId(),
                entity.getType().name()
        );
    }

}
