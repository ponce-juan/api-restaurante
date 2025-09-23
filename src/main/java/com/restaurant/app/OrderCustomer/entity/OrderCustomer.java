package com.restaurant.app.OrderCustomer.entity;

import com.restaurant.app.Customer.entity.Customer;
import com.restaurant.app.OrderItem.entity.OrderItem;
import com.restaurant.app.OrderStatus.entity.OrderStatus;
import com.restaurant.app.OrderType.entity.OrderType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "order_customers")
public class OrderCustomer
{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
//Many OrderCustomer can have one Customer
    @ManyToOne
    @JoinColumn(name="customer_id", nullable = false)
    private Customer customer;
//Many OrderCustomer can have one OrderType
    @ManyToOne
    @JoinColumn(
        name="order_type_id",
        nullable = false)
    private OrderType orderType;
//Many OrderCustomer can have one OrderStatus
    @ManyToOne
    @JoinColumn(
        name="order_status_id",
        nullable = false)
    private OrderStatus orderStatus;
// One OrderCustomer can have many OrderItems
    @OneToMany(
        mappedBy = "orderCustomer",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    private BigDecimal totalAmount;


    public void calculateTotalAmount(){
        totalAmount = BigDecimal.ZERO;
        for (OrderItem orderItem : orderItems) {
            totalAmount = totalAmount.add(orderItem.getSubTotal());
        }
    }

}
