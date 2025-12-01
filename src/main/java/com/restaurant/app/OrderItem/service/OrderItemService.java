package com.restaurant.app.OrderItem.service;


import com.restaurant.app.OrderItem.dto.OrderItemRequest;
import com.restaurant.app.OrderItem.entity.OrderItem;
import lombok.NonNull;

import java.util.List;

public interface OrderItemService
{
    List<OrderItem> getByOrderCustomerId(@NonNull Long orderCustomerId);
    OrderItem getOrderItemById(@NonNull Long id);
    OrderItem addOrderItem(OrderItemRequest orderItem);
    OrderItem updateOrderItem(Long id, OrderItemRequest orderItem);
    void deleteOrderItemById(@NonNull Long id);
}
