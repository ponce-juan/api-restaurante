package com.restaurant.app.OrderStatus.service;

import com.restaurant.app.OrderStatus.entity.OrderStatus;
import com.restaurant.app.OrderStatus.model.OrderStatusEnum;
import com.restaurant.app.OrderStatus.repository.OrderStatusRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusServiceImp implements OrderStatusService
{
    // Inyeccion de dependencias
    private final OrderStatusRepository orderStatusRepository;

    public OrderStatusServiceImp(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }


    @Override
    public List<OrderStatus> getAllOrderStatus ()
    {
        return orderStatusRepository.findAll();
    }

    @Override
    public OrderStatus getOrderStatusById (@NonNull Long id)
    {
        return orderStatusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order status not found with id: " + id));
    }

    @Override
    public OrderStatus getOrderStatusByName (@NonNull OrderStatusEnum name)
    {
        return orderStatusRepository.findByStatusName(name)
                .orElseThrow(() -> new EntityNotFoundException("Order status not found with name: " + name));
    }

    @Override
    public OrderStatus createOrderStatus (@NonNull OrderStatus orderStatus)
    {
        // Validar que el nombre del estado no exista
        OrderStatusEnum statusEnum = orderStatus.getStatusName();
        orderStatusRepository.findByStatusName(statusEnum)
                .ifPresent(existingStatus -> {
                    throw new IllegalArgumentException("Order status already exists with name: " + statusEnum.name());
                });
        // Guardo el nuevo estado
        return orderStatusRepository.save(orderStatus);
    }

    @Override
    public OrderStatus updateOrderStatus (@NonNull Long id, @NonNull OrderStatus orderStatus)
    {
        // Busco el estado por id
        OrderStatus existingOrderStatus = orderStatusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order status not found with id: " + id));

        // Verifico si el nombre del estado ya existe
        OrderStatusEnum statusEnum = orderStatus.getStatusName();
        // Verifico que el nuevo estado no sea null
        if (statusEnum == null) throw new IllegalArgumentException("Order status cannot be null");

        orderStatusRepository.findByStatusName(statusEnum)
                .ifPresent(existingStatus -> {
                    if (!existingStatus.getId().equals(id)) {
                        throw new IllegalArgumentException("Order status already exists with name: " + statusEnum.name());
                    }
                });

        // Actualizo el estado
        existingOrderStatus.setStatusName(statusEnum);

        return orderStatusRepository.save(existingOrderStatus);
    }

    @Override
    public void deleteOrderStatus (@NonNull Long id)
    {
        // Busco el estado por id
        OrderStatus existingOrderStatus = orderStatusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order status not found with id: " + id));

        // Elimino el estado
        orderStatusRepository.delete(existingOrderStatus);
    }
}
