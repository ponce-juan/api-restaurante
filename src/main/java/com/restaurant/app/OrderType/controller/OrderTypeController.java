package com.restaurant.app.OrderType.controller;

import com.restaurant.app.OrderType.entity.OrderType;
import com.restaurant.app.OrderType.model.OrderTypeEnum;
import com.restaurant.app.OrderType.service.OrderTypeService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-types")
public class OrderTypeController
{
    // Dependency Injection
    private final OrderTypeService orderTypeService;
    //
     public OrderTypeController(OrderTypeService orderTypeService)
     {
         this.orderTypeService = orderTypeService;
     }

     //Get all order types
     @GetMapping
     public List<OrderType> getAllOrderTypes()
     {
         try{
            return orderTypeService.getAllOrderTypes();
         } catch ( EntityNotFoundException e ){
                System.out.println(e.getMessage());
                return null;
         }
     }
    //Get order type by id
    @GetMapping("/{id}")
    public OrderType getOrderTypeById(@PathVariable Long id){
        return orderTypeService.getOrderTypeById(id);
    }

    //Get order type by name
    @GetMapping("/name/{name}")
    public OrderType getOrderTypeByType(@PathVariable String type){
        OrderTypeEnum typeEnum = OrderTypeEnum.valueOf(type.toUpperCase());
        return orderTypeService.getOrderTypeByType(typeEnum);
    }

    //Create order type
    @PostMapping
    public OrderType createOrderType(@RequestBody OrderType orderType){
         try{
            return orderTypeService.createOrderType(orderType);
         }
         catch ( Exception e )
         {
            System.out.println(e.getMessage());
            return null;
         }
    }

    //Update order type
    @PutMapping("/{id}")
    public OrderType updateOrderType(@PathVariable Long id, @RequestBody OrderType orderType){
        return orderTypeService.updateOrderType(id, orderType);
    }

    //Delete order type
    @DeleteMapping("/{id}")
    public void deleteOrderType(@PathVariable Long id){
        orderTypeService.deleteOrderType(id);
    }

}
