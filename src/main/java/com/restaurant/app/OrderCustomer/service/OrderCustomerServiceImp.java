package com.restaurant.app.OrderCustomer.service;

import com.restaurant.app.OrderCustomer.entity.OrderCustomer;
import com.restaurant.app.OrderCustomer.repository.OrderCustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderCustomerServiceImp implements OrderCustomerService
{
    //Dependency Injection
    private final OrderCustomerRepository orderCustomerRepository;

    public OrderCustomerServiceImp (OrderCustomerRepository orderCustomerRepository){
        this.orderCustomerRepository = orderCustomerRepository;
    }

    @Override
    public OrderCustomer createOrderCustomer (@NonNull OrderCustomer orderCustomer)
    {
        orderCustomer.setOrderDate(LocalDateTime.now());
        return orderCustomerRepository.save(orderCustomer);
    }

    @Override
    @Transactional
    public OrderCustomer updateOrderCustomer (@NonNull Long id, @NonNull OrderCustomer orderCustomer)
    {
        OrderCustomer orderCustomerDb = orderCustomerRepository
                                            .findById(id)
                                            .orElse(null);
        if(orderCustomerDb != null){
            orderCustomerDb.setCustomer(orderCustomer.getCustomer());
            orderCustomerDb.setOrderType(orderCustomer.getOrderType());
            orderCustomerDb.setOrderStatus(orderCustomer.getOrderStatus());
            orderCustomerDb.setOrderItems(orderCustomer.getOrderItems());
//            No actualizo la fecha de creacion de la orden
//            orderCustomerDb.setOrderDate(orderCustomer.getOrderDate());
            orderCustomerDb.calculateTotalAmount();
            return orderCustomerRepository.save(orderCustomerDb);
        }
        throw new EntityNotFoundException("OrderCustomer not found with id: " + orderCustomer.getId());
    }

    @Override
    public OrderCustomer getOrderCustomerById (@NonNull Long id)
    {
        return orderCustomerRepository
                   .findById(id)
                   .orElseThrow(() -> new EntityNotFoundException("OrderCustomer not found with id: " + id));
    }

    @Override
    public List<OrderCustomer> getAllOrderCustomers ()
    {
        return orderCustomerRepository.findAll();
    }

    @Override
    public List<OrderCustomer> getOrderCustomersByStatusId (@NonNull Long orderStatusId)
    {
        return orderCustomerRepository
                   .findByOrderStatus_Id(orderStatusId)
                   .orElseThrow(() -> new EntityNotFoundException("OrderCustomers not found with status id: " + orderStatusId));
    }

    @Override
    public List<OrderCustomer> getOrderCustomersByStatusIdAndCustomerId (@NonNull Long orderStatusId,
                                                                       @NonNull Long customerId)
    {
        return orderCustomerRepository.findByOrderStatus_IdAndCustomer_Id(orderStatusId, customerId)
                   .orElseThrow(
                       () -> new EntityNotFoundException("OrderCustomers not found with status id: " + orderStatusId + " and customer id: " + customerId)
                   );
    }

    @Override
    public List<OrderCustomer> getOrderCustomersByOrderTypeId (@NonNull Long orderTypeId)
    {
        return orderCustomerRepository
                   .findByOrderType_Id(orderTypeId)
                   .orElseThrow(
                       () -> new EntityNotFoundException("OrderCustomers not found with order type id: " + orderTypeId)
                   );
    }

    @Override
    public void deleteOrderCustomer (@NonNull Long id)
    {
        OrderCustomer orderCustomer = orderCustomerRepository
                                          .findById(id)
                                          .orElseThrow(
                                              () -> new EntityNotFoundException("OrderCustomer not found with id: " + id)
                                          );

        orderCustomerRepository.delete(orderCustomer);
    }
}
