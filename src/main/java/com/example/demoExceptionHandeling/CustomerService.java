package com.example.demoExceptionHandeling;

// import org.springframework.stereotype.Service;

public interface CustomerService {
    //get all customer by its id
    Customer getCustomer(Long id);
    //add new customer
    String addCustomer(Customer customer);
    //update details for customer
    String updateCustomer(Long id, Customer customer);
}
