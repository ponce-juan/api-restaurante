package com.restaurant.app.Customer.controller;

import com.restaurant.app.Customer.entity.Customer;
import com.restaurant.app.Customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController
{
    //Inyeccion de dependencia de CustomerService en constructor
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
        System.out.println("************************");
        System.out.println("customer controller init");
        System.out.println("************************");
    }

    @PostMapping
    public Customer createCustomer(@Valid @RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customer){
        return customerService.updateCustomer(id, customer);
    }
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }


}
