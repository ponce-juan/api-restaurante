package com.restaurant.app.OrderCustomer.service;

import com.restaurant.app.OrderCustomer.entity.OrderCustomer;
import lombok.NonNull;

import java.util.List;

public interface OrderCustomerService
{
    OrderCustomer createOrderCustomer(@NonNull OrderCustomer orderCustomer);
    OrderCustomer updateOrderCustomer(@NonNull Long id, @NonNull OrderCustomer orderCustomer);
    OrderCustomer getOrderCustomerById(@NonNull Long id);
    List<OrderCustomer> getAllOrderCustomers();
    List<OrderCustomer> getOrderCustomersByStatusId(@NonNull Long orderStatusId);
    List<OrderCustomer> getOrderCustomersByStatusIdAndCustomerId(@NonNull Long orderStatusId, @NonNull Long customerId);
    List<OrderCustomer> getOrderCustomersByOrderTypeId(@NonNull Long orderTypeId);
    void deleteOrderCustomer(@NonNull Long id);
}
