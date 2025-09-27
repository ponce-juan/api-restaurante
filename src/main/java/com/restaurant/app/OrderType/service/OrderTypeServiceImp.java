package com.restaurant.app.OrderType.service;

import com.restaurant.app.OrderType.entity.OrderType;
import com.restaurant.app.OrderType.model.OrderTypeEnum;
import com.restaurant.app.OrderType.repository.OrderTypeRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderTypeServiceImp implements OrderTypeService
{
    // Dependency Injection
    private final OrderTypeRepository orderTypeRepository;

    public OrderTypeServiceImp (OrderTypeRepository orderTypeRepository)
    {
        this.orderTypeRepository = orderTypeRepository;
    }


    @Override
    public List<OrderType> getAllOrderTypes ()
    {

        List<OrderType> orderTypeList = orderTypeRepository.findAll();

        if(!orderTypeList.isEmpty())
            return orderTypeList;

        throw new EntityNotFoundException("No Order Types found");
    }

    @Override
    public OrderType getOrderTypeById (@NonNull Long id)
    {
        return orderTypeRepository
                   .findById(id)
                   .orElseThrow(
                       () -> new EntityNotFoundException("Order Type not found with id: " + id)
                   );
    }

    @Override
    public OrderType getOrderTypeByType (@NonNull OrderTypeEnum typeEnum)
    {
        return orderTypeRepository
                   .findByType(typeEnum)
                   .orElseThrow(
                       () -> new EntityNotFoundException("Order Type not found with name: " + typeEnum.name())
                   );
    }

    @Override
    public OrderType createOrderType (@NonNull OrderType orderType)
    {
        //Si el nombre ya existe, lanzamos una excepción
        OrderTypeEnum typeEnum = orderType.getType();
        if (typeEnum == null) throw new IllegalArgumentException("Order type cannot be null");

        orderTypeRepository.findByType(typeEnum)
            .ifPresent(
                existingOrderType -> {
                    throw new EntityExistsException("Order Type already exists with name: " + typeEnum.name());
                }
            );

        //Si no existe, lo guardamos
        return orderTypeRepository.save(orderType);
    }

    @Transactional
    @Override
    public OrderType updateOrderType (@NonNull Long id, @NonNull OrderType orderType)
    {
        //Verificamos que el nuevo ordertype no sea null
        OrderTypeEnum typeEnum = orderType.getType();
        if(typeEnum == null) throw new IllegalArgumentException("Order type cannot be null");
        //Verificamos si existe el nombre
        orderTypeRepository.findByType(typeEnum)
            .ifPresent(
                existingOrderType -> {
                    throw new EntityExistsException("Order Type already exists with name: " + typeEnum.name());
                }
            );

        //Verificamos si existe el id
        OrderType orderTypeDb = orderTypeRepository
                                           .findById(id)
                                           .orElseThrow(
                                               () -> new EntityNotFoundException("Order Type not found with id: " + id)
                                           );


        //Si existe, modificamos el campo y guardamos
        orderTypeDb.setType(typeEnum);

        //Retornamos el objeto, ya que se guarda automaticamente por Transactional
        return orderTypeDb;
    }

    @Override
    public void deleteOrderType (@NonNull Long id)
    {
        //Verificamos si existe el id
        OrderType orderTypeDb = orderTypeRepository
                                           .findById(id)
                                           .orElseThrow(
                                               () -> new EntityNotFoundException("Order Type not found with id: " + id)
                                           );

        //Si existe, lo eliminamos
        orderTypeRepository.delete(orderTypeDb);
        
    }
}
