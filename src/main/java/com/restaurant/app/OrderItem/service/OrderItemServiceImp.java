package com.restaurant.app.OrderItem.service;

import com.restaurant.app.OrderItem.entity.OrderItem;
import com.restaurant.app.Product.entity.Product;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImp implements OrderItemService
{

    @Override
    public List<OrderItem> getOrderItemsByOrderCustomerId (@NonNull Long orderCustomerId)
    {
        return List.of();
    }

    @Override
    public List<Product> getProductsByOrderCustomerId (@NonNull Long orderCustomerId)
    {
        return List.of();
    }
}
