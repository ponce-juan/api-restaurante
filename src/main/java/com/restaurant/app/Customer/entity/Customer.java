package com.restaurant.app.Customer.entity;

import com.restaurant.app.OrderCustomer.entity.OrderCustomer;
import com.restaurant.app.common.bases.Person;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="customers")
public class Customer extends Person
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//One Customer can have many OrderCustomer
    @OneToMany(
        mappedBy = "customer",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        orphanRemoval = true)
    private List<OrderCustomer> orderCustomers = new ArrayList<>();
}
