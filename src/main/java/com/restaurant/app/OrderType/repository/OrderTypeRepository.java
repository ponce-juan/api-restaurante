package com.restaurant.app.OrderType.repository;

import com.restaurant.app.OrderType.entity.OrderType;
import com.restaurant.app.OrderType.model.OrderTypeEnum;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderTypeRepository extends JpaRepository<OrderType, Long>
{
    Optional<OrderType> findByType(@NonNull OrderTypeEnum typeEnum);
}

