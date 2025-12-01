package com.restaurant.app.OrderCustomer.dto;

import com.restaurant.app.OrderCustomer.entity.OrderCustomer;
import com.restaurant.app.OrderItem.dto.ItemDTO;
import com.restaurant.app.OrderStatus.dto.OrderStatusMapper;
import com.restaurant.app.OrderType.dto.OrderTypeMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderCustomerMapper {
    public static OrderCustomerResponse toResponse(OrderCustomer entity){
        return new OrderCustomerResponse(
                entity.getId(),
//                entity.getCustomer() != null ? entity.getCustomer().getName() : "Guest",
                OrderTypeMapper.toDTO(entity.getType()),
                OrderStatusMapper.toDTO(entity.getStatus()),
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
