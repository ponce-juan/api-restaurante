package com.restaurant.app.OrderCustomer.controller;

import com.restaurant.app.OrderCustomer.entity.OrderCustomer;
import com.restaurant.app.OrderCustomer.service.OrderCustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-customers")
public class OrderCustomerController
{
    //Dependency Injection
    private final OrderCustomerService orderCustomerService;
    public OrderCustomerController(OrderCustomerService orderCustomerService)
    {
        this.orderCustomerService = orderCustomerService;
    }

    //Get all order customers
    @GetMapping
    public List<OrderCustomer> getAllOrderCustomers(){
        return orderCustomerService.getAllOrderCustomers();
    }

    //Get order customer by id
    @GetMapping("/{id}")
    public ResponseEntity<OrderCustomer> getOrderCustomerById(@PathVariable Long id){

        try {
            OrderCustomer orderCustomer = orderCustomerService.getOrderCustomerById(id);
            return ResponseEntity.ok(orderCustomer);

        } catch ( EntityNotFoundException e){
            // Handle the exception and return a 404 Not Found response
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    //Get orders customer by status id and customer id
    @GetMapping("/status/{orderStatusId}/customer/{customerId}")
    public ResponseEntity<List<OrderCustomer>> getOrderCustomerByStatusIdAndCustomerId(
            @PathVariable Long orderStatusId,
            @PathVariable Long customerId)
    {
        try {
            List<OrderCustomer> orderCustomers = orderCustomerService.getOrderCustomersByStatusIdAndCustomerId(orderStatusId, customerId);
            return ResponseEntity.ok(orderCustomers);
        }
        catch ( EntityNotFoundException e )
        {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    //Get orders customer by order type id
    @GetMapping("/type/{orderTypeId}")
    public ResponseEntity<List<OrderCustomer>> getOrderCustomersByOrderTypeId(@PathVariable Long orderTypeId){
        try {
            List<OrderCustomer> orderCustomers = orderCustomerService.getOrderCustomersByOrderTypeId(orderTypeId);
            return ResponseEntity.ok(orderCustomers);
        }
        catch ( EntityNotFoundException e )
        {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    //Get orders customer by status id
    @GetMapping("/status/{orderStatusId}")
    public ResponseEntity<List<OrderCustomer>> getOrderCustomersByStatusId(@PathVariable Long orderStatusId){
        try {
            List<OrderCustomer> orderCustomers = orderCustomerService.getOrderCustomersByStatusId(orderStatusId);
            return ResponseEntity.ok(orderCustomers);
        }
        catch ( EntityNotFoundException e )
        {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    //Create order customer
    @PostMapping
    public ResponseEntity<OrderCustomer> createOrderCustomer(@RequestBody OrderCustomer orderCustomer){
        try {
            OrderCustomer createdOrderCustomer = orderCustomerService.createOrderCustomer(orderCustomer);
            return ResponseEntity.ok(createdOrderCustomer);
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    //Update order customer
    @PutMapping("/{id}")
    public ResponseEntity<OrderCustomer> updateOrderCustomer(@PathVariable Long id, @RequestBody OrderCustomer orderCustomer){
        try {

            OrderCustomer updatedOrderCustomer = orderCustomerService.updateOrderCustomer(id, orderCustomer);
            return ResponseEntity.ok(updatedOrderCustomer);
        }
        catch ( EntityNotFoundException e )
        {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    //Delete order customer
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderCustomer(@PathVariable Long id){
        try {
            orderCustomerService.deleteOrderCustomer(id);
            return ResponseEntity.noContent().build();
        }
        catch ( EntityNotFoundException e )
        {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }




}
