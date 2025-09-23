package com.restaurant.app.OrderStatus.repository;

import com.restaurant.app.OrderStatus.entity.OrderStatus;
import com.restaurant.app.OrderStatus.model.OrderStatusEnum;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long>
{
    // Aqui se pueden agregar metodos personalizados si es necesario
    // Por ejemplo, buscar por nombre de estado
    Optional<OrderStatus> findByStatusName(@NonNull OrderStatusEnum statusName);
}
