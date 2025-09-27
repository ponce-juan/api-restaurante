package com.restaurant.app.Customer.repository;

import com.restaurant.app.Customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    //find customer by dni
    Customer findByDni(String dni);
}
