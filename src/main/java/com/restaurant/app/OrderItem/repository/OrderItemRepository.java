package com.restaurant.app.OrderItem.repository;

import com.restaurant.app.OrderItem.entity.OrderItem;
import com.restaurant.app.Product.entity.Product;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>
{
    List<Product> findByOrderCustomerId(@NonNull Long orderCustomerId);
}
