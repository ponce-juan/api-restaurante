package com.restaurant.app.OrderType.service;

import com.restaurant.app.OrderType.entity.OrderType;
import com.restaurant.app.OrderType.model.OrderTypeEnum;
import lombok.NonNull;

import java.util.List;

public interface OrderTypeService
{
    List<OrderType> getAllOrderTypes();
    OrderType getOrderTypeById(@NonNull Long id);
    OrderType getOrderTypeByType(@NonNull OrderTypeEnum typeEnum);
    OrderType createOrderType(@NonNull OrderType orderType);
    OrderType updateOrderType(@NonNull Long id, @NonNull OrderType orderType);
    void deleteOrderType(@NonNull Long id);
}
