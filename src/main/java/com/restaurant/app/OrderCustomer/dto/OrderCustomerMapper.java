package com.restaurant.app.OrderCustomer.dto;

import com.restaurant.app.OrderCustomer.entity.OrderCustomer;
import com.restaurant.app.OrderItem.dto.ItemDTO;
import com.restaurant.app.OrderStatus.dto.OrderStatusDTO;
import com.restaurant.app.OrderType.dto.OrderTypeDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderCustomerMapper {
    public static OrderCustomerResponse toResponse(OrderCustomer entity){
        return new OrderCustomerResponse(
                entity.getId(),
                entity.getCustomer().getName(),
                new OrderTypeDTO(entity.getType().getId(), entity.getType().toString()),
                new OrderStatusDTO(entity.getStatus().getId(), entity.getStatus().toString()),
                entity.getTotalAmount(),
                entity.getItems().stream()
                        .map(item -> new ItemDTO(item.getProduct().getName(), item.getQuantity(), item.getProduct().getPrice()))
                        .toList()
                );
    }

//    public OrderCustomer toEntity(OrderCustomerRequest req){
//        OrderCustomer entity = new OrderCustomer();
//        entity.setId(req.id());
//        entity.setCustomer(null); // Customer should be set separately
//        entity.setStatus(OrderStatus)
//        entity.setItems(req.items());
//
//
//        return entity;
//    }
}
