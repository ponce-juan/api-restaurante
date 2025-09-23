package com.restaurant.app.Delivery.entity;

import com.restaurant.app.Employee.entity.Employee;
import com.restaurant.app.OrderCustomer.entity.OrderCustomer;
import com.restaurant.app.OrderStatus.entity.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "deliveries")
public class Delivery
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_customer_id", referencedColumnName = "id")
    private OrderCustomer orderCustomer;

    @OneToOne
    @JoinColumn(name = "delivery_person_id", referencedColumnName = "id")
    private Employee deliveryPerson;

    @OneToOne
    @JoinColumn(name = "order_status_id", referencedColumnName = "id")
    private OrderStatus orderStatus;

    private LocalDateTime deliveryDate;

}
