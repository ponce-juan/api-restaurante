package com.restaurant.app.Customer.service;

import com.restaurant.app.Customer.entity.Customer;
import com.restaurant.app.Customer.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService
{
    //Inyeccion de dependencia de CustomerRepository en constructor
    private final CustomerRepository customerRepository;

    public CustomerServiceImp (CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    //Get all customers
    @Override
    public List<Customer> getAllCustomers (){
        return customerRepository.findAll();
    }

    //Get customer by id
    @Override
    public Customer getCustomerById(@NonNull Long id){
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    //Create customer
    @Override
    public Customer createCustomer(@Valid @NonNull Customer customer){
        //Si el cliente no existe, lo agrego
        Customer customerDb = customerRepository.findByDni(customer.getDni());
        return customerDb == null ? customerRepository.save(customer) : null;
    }

    //Update customer
    @Override
    public Customer updateCustomer(@NonNull Long id,@Valid @NonNull Customer customer){
        //Obtengo el cliente de la db, lanza error si no existe con el id
        Customer customerDb = getCustomerById(id);

        //Actualizo los datos del cliente
        customerDb.setEmail(customer.getEmail());
        customerDb.setName(customer.getName());
        customerDb.setPhone(customer.getPhone());
        customerDb.setLastName(customer.getLastName());
        customerDb.setDni(customer.getDni());

        customerDb.setAddress(customer.getAddress());
        //Guardo los cambios en la db
        return customerRepository.save(customerDb);
    }

    //Delete customer
    @Override
    public void deleteCustomer(@NonNull Long id){
        //Obtengo el cliente de la db
        Customer customer = customerRepository
                                .findById(id)
                                .orElseThrow(
                                    () -> new EntityNotFoundException("Customer not found with id: " + id)
                                );
        //Elimino el cliente de la db
        customerRepository.delete(customer);
    }



}
