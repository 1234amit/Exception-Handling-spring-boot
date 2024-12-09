package com.example.demoExceptionHandeling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import com.customer.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    boolean existsByEmail(String email); 
}
