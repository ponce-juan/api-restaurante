package com.restaurant.app.OrderItem.service;

import com.restaurant.app.OrderCustomer.entity.OrderCustomer;
import com.restaurant.app.OrderCustomer.repository.OrderCustomerRepository;
import com.restaurant.app.OrderItem.dto.OrderItemRequest;
import com.restaurant.app.OrderItem.entity.OrderItem;
import com.restaurant.app.OrderItem.repository.OrderItemRepository;
import com.restaurant.app.Product.entity.Product;
import com.restaurant.app.Product.repository.ProductRepository;
import com.restaurant.app.Utils.SecurityUtils;
import com.restaurant.app.common.embedded.OrderItemId;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImp implements OrderItemService
{
    private final OrderItemRepository orderItemRepository;
    private final OrderCustomerRepository orderCustomerRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderItem addOrderItem(OrderItemRequest request) {

        Long companyId = SecurityUtils.getCompanyId();
        Product existingProduct = null;

        OrderCustomer order = orderCustomerRepository.findById(request.orderCustomerId())
                .orElseThrow(() -> new RuntimeException("Order customer not found"));


        if(request.productName() != null && !request.productName().isEmpty()){
            existingProduct = productRepository.findByNameIgnoreCaseAndCompanyId(request.productName(), companyId);
        }

        if(existingProduct == null){
            throw new RuntimeException("Product not found");
        }

        OrderItem item = new OrderItem();
        item.setOrderCustomer(order);
        item.setProduct(existingProduct);
        item.setPrice(request.price());
        item.setQuantity(request.quantity());

        item.calculateSubTotal();

        return orderItemRepository.save(item);
    }

    @Override
    public List<OrderItem> getByOrderCustomerId(@NonNull Long orderCustomerId)
    {
        return orderItemRepository.findByOrderCustomerId(orderCustomerId);
    }

    @Override
    public OrderItem getOrderItemById(@NonNull Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Order Item with id " + id + " not found."));
    }

//    @Override
//    public OrderItem addOrderItem(@NonNull OrderItem orderItem) {
//        orderItem.calculateSubTotal();
//        return orderItemRepository.save(orderItem);
//    }

    @Override
    public OrderItem updateOrderItem(Long id, OrderItemRequest request) {

        Long companyId = SecurityUtils.getCompanyId();
        OrderItem existing = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (request.productName() != null) {
            Product product = productRepository.findByNameIgnoreCaseAndCompanyId(request.productName(), companyId);
            if (product == null) throw new RuntimeException("Product not found");
            existing.setProduct(product);
        }

        existing.setQuantity(request.quantity());
        existing.setPrice(request.price());
        existing.calculateSubTotal();

        return orderItemRepository.save(existing);
    }

    @Override
    public void deleteOrderItemById(@NonNull Long id) {
        orderItemRepository.deleteById(id);
    }

}
