//package com.restaurant.app.OrderCustomer.service;
//
//import com.restaurant.app.OrderCustomer.entity.OrderCustomer;
//import lombok.NonNull;
//
//import java.util.List;
//
//public interface OrderCustomerService
//{
//    OrderCustomer createOrderCustomer(@NonNull OrderCustomer orderCustomer);
//    OrderCustomer updateOrderCustomer(@NonNull Long id, @NonNull OrderCustomer orderCustomer);
//    OrderCustomer getOrderCustomerById(@NonNull Long id);
//    List<OrderCustomer> getAllOrderCustomers();
//    List<OrderCustomer> getOrderCustomersByStatusId(@NonNull Long orderStatusId);
//    List<OrderCustomer> getOrderCustomersByStatusIdAndCustomerId(@NonNull Long orderStatusId, @NonNull Long customerId);
//    List<OrderCustomer> getOrderCustomersByOrderTypeId(@NonNull Long orderTypeId);
//    void deleteOrderCustomer(@NonNull Long id);
//}

package com.restaurant.app.OrderCustomer.service;

import com.restaurant.app.OrderCustomer.dto.OrderCustomerRequest;
import com.restaurant.app.OrderCustomer.dto.OrderCustomerResponse;
import com.restaurant.app.OrderCustomer.entity.OrderCustomer;

import java.util.List;

public interface OrderCustomerService {

    OrderCustomerResponse createOrderCustomer(OrderCustomerRequest request);

    OrderCustomerResponse updateOrderCustomer(Long id, OrderCustomerRequest request);

    OrderCustomerResponse getOrderCustomerById(Long id);

    List<OrderCustomerResponse> getAllOrderCustomers();

    List<OrderCustomerResponse> getOrderCustomersByStatusId(Long orderStatusId);

//    List<OrderCustomerResponse> getOrderCustomersByStatusIdAndCustomerId(Long orderStatusId, Long customerId);

    List<OrderCustomerResponse> getOrderCustomersByOrderTypeId(Long orderTypeId);

    void deleteOrderCustomer(Long id);
}
