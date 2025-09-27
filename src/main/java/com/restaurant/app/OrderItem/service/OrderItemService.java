package com.restaurant.app.OrderItem.service;


import com.restaurant.app.OrderItem.entity.OrderItem;
import com.restaurant.app.Product.entity.Product;
import lombok.NonNull;

import java.util.List;

public interface OrderItemService
{
    List<OrderItem> getOrderItemsByOrderCustomerId(@NonNull Long orderCustomerId);
    List<Product> getProductsByOrderCustomerId(@NonNull Long orderCustomerId);
}
