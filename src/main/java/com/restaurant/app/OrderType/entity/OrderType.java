package com.restaurant.app.OrderType.entity;

import com.restaurant.app.OrderCustomer.entity.OrderCustomer;
import com.restaurant.app.OrderType.model.OrderTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/*
* Valores posibles para el tipo de orden:
* 1. Take away - Para llevar
* 2. Delivery - A domicilio
* 3. Dine in - Para comer en el local
* 4. Reservation - Reserva
* */

@Data
@Entity
@Table(name = "order_types")
public class OrderType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "name", nullable = false, unique = true)
//    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name="type_name", nullable = false, unique = true)
    private OrderTypeEnum type;

    //Relacion muchas OrderCustomer pueden tener un mismo OrderType
    @OneToMany(
        mappedBy = "orderType",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        orphanRemoval = true)
    private List<OrderCustomer> orders = new ArrayList<>();

}
