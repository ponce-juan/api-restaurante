package com.restaurant.app.OrderItem.controller;

import com.restaurant.app.OrderItem.dto.OrderItemRequest;
import com.restaurant.app.OrderItem.entity.OrderItem;
import com.restaurant.app.OrderItem.service.OrderItemServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemServiceImp orderItemServiceImp;

    @PostMapping
    public ResponseEntity<OrderItem> create(@RequestBody OrderItemRequest request) {
        return ResponseEntity.ok(orderItemServiceImp.addOrderItem(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> update(
            @PathVariable Long id,
            @RequestBody OrderItemRequest request
    ) {
        return ResponseEntity.ok(orderItemServiceImp.updateOrderItem(id, request));
    }

    @GetMapping("/order/{orderCustomerId}")
    public ResponseEntity<List<OrderItem>> getItems(
            @PathVariable Long orderCustomerId
    ) {
        return ResponseEntity.ok(orderItemServiceImp.getByOrderCustomerId(orderCustomerId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        orderItemServiceImp.deleteOrderItemById(id);
        return ResponseEntity.noContent().build();
    }
}
