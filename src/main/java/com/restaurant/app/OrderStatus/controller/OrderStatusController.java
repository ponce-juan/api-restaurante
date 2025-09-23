package com.restaurant.app.OrderStatus.controller;

import com.restaurant.app.OrderStatus.entity.OrderStatus;
import com.restaurant.app.OrderStatus.model.OrderStatusEnum;
import com.restaurant.app.OrderStatus.service.OrderStatusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-status")
public class OrderStatusController
{
    private final OrderStatusService orderStatusService;
    public OrderStatusController(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    //Get all order status
    @GetMapping
    public List<OrderStatus> getAllOrderStatus() {
        return orderStatusService.getAllOrderStatus();
    }
    //Get order status by id
    @GetMapping("/{id}")
    public OrderStatus getOrderStatusById(@PathVariable Long id) {
        return orderStatusService.getOrderStatusById(id);
    }
    //Get order status by name
    @GetMapping("/name/{name}")
    public OrderStatus getOrderStatusByName(@PathVariable String name) {
        OrderStatusEnum orderStatusEnum = OrderStatusEnum.valueOf(name.toUpperCase());
        return orderStatusService.getOrderStatusByName(orderStatusEnum);
    }

    //Create order status
    @PostMapping
    public OrderStatus createOrderStatus(@RequestBody OrderStatus orderStatus) {
        return orderStatusService.createOrderStatus(orderStatus);
    }

    //Update order status
    @PutMapping("/{id}")
    public OrderStatus updateOrderStatus(@PathVariable Long id, @RequestBody OrderStatus orderStatus) {
        return orderStatusService.updateOrderStatus(id, orderStatus);
    }

    //Delete order status
    @DeleteMapping("/{id}")
    public void deleteOrderStatus(@PathVariable Long id) {
        orderStatusService.deleteOrderStatus(id);
    }

}
