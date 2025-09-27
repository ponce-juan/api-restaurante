package com.restaurant.app.Customer.service;

import com.restaurant.app.Customer.entity.Customer;
import lombok.NonNull;

import java.util.List;

public interface CustomerService
{
     List<Customer> getAllCustomers();
     Customer getCustomerById(@NonNull Long id);
     Customer createCustomer(@NonNull Customer customer);
     Customer updateCustomer(@NonNull Long id, @NonNull Customer customer);
     void deleteCustomer(@NonNull Long id);
}
