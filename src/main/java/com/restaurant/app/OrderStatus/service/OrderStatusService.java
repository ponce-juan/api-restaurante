package com.restaurant.app.OrderStatus.service;

import com.restaurant.app.OrderStatus.entity.OrderStatus;
import com.restaurant.app.OrderStatus.model.OrderStatusEnum;
import lombok.NonNull;

import java.util.List;

public interface OrderStatusService
{
    List<OrderStatus> getAllOrderStatus();
    OrderStatus getOrderStatusById(@NonNull Long id);
    OrderStatus getOrderStatusByName(@NonNull OrderStatusEnum name);
    OrderStatus createOrderStatus(@NonNull OrderStatus orderStatus);
    OrderStatus updateOrderStatus(@NonNull Long id, @NonNull OrderStatus orderStatus);
    void deleteOrderStatus(@NonNull Long id);
}
