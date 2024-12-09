package com.example.demoExceptionHandeling;

import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getCustomer(Long id){
        return customerRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Customer found for id " + id));
    }

    //add customer
    // @Override
    // public String addCustomer(Customer customer){
    //     Customer existingCustomer = customerRepository.findById(customer.getId()).orElse(null);
    //     if(existingCustomer == null){
    //         customerRepository.save(customer);
    //         return "Customer added successfully";
    //     }else{
    //         throw new CustomerAlreadyExistsException("Customer already exists!");
    //     }
    // }

    // @Override
    // @Transactional
    // public String addCustomer(Customer customer) {
    //    try {
    //     Customer existingCustomer = customerRepository.findById(customer.getId()).orElse(null);
            
    //     if(existingCustomer == null){
    //                 customerRepository.save(customer);
    //                 return "Customer added successfully";
    //             }else{
    //                 throw new CustomerAlreadyExistsException("Customer already exists!");
    //             }
    //     } catch (OptimisticLockException e) {
    //         return "Error saving Customer: Another transaction modified the entity.";
    //     } catch (Exception e) {
    //         return "Error saving Customer: " + e.getMessage();
    //     }
    // }

    @Override
    @Transactional
    public String addCustomer(Customer customer) {
        try {
            if (customerRepository.existsByEmail(customer.getEmail())) {
                throw new CustomerAlreadyExistsException(
                        "Customer already exists with email: " + customer.getEmail());
            }

            Customer customerEntity = new Customer();
            BeanUtils.copyProperties(customer, customerEntity);

            if (customerEntity.getId() != null) {
                // Fetch existing entity and update only allowed fields
                Customer existingEntity = customerRepository.findById(customerEntity.getId()).orElse(null);
                if (existingEntity != null) {
                    existingEntity.setId(customerEntity.getId());
                    existingEntity.setName(customerEntity.getName());
                    existingEntity.setEmail(customerEntity.getEmail());
                    existingEntity.setAddress(customerEntity.getAddress());
                    customerEntity = existingEntity; // Use the attached entity
                }
            }

            customerRepository.save(customerEntity);
            return "Saved successfully";
        } catch (OptimisticLockException e) {
            return "Error saving employee: Another transaction modified the entity.";
        } catch (Exception e) {
            throw new RuntimeException("Failed to add customer", e); // Properly wrap and propagate
        }
    }


    //update customer
    @Override
    public String updateCustomer(Long id, Customer customer){
        // Customer existingCustomer = customerRepository.findById(customer.getId()).orElse(null);
        // if(existingCustomer == null){
        //     throw new NoSuchCustomerExistsException("No Such customer exists!");
        // }else{
        //     existingCustomer.setName(customer.getName());
        //     existingCustomer.setEmail(customer.getEmail());
        //     existingCustomer.setAddress(customer.getAddress());
        //     customerRepository.save(existingCustomer);

        //     return "Customer Record Updated Successfully";
        // }

        try{
            Customer existingEmployee = customerRepository.findById(id).get();
            existingEmployee.setName(customer.getName());
            existingEmployee.setEmail(customer.getEmail());
            existingEmployee.setAddress(customer.getAddress());
            customerRepository.save(existingEmployee);
            return "Customer Updated successfully";
        }catch(Exception e){
            return "Error updating Customer: " + e.getMessage();
        }
    }

} 
