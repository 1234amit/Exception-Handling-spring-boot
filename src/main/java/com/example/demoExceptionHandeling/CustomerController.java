package com.example.demoExceptionHandeling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //get customer by id
    @GetMapping("/getCustomer/{id}")
    public Customer getCustomer(@PathVariable("id") Long id){
        return customerService.getCustomer(id);
    }

    //submit customer 
    // @PostMapping("/addCustomer")
    // public String addCustomer(@RequestBody Customer customer){
    //     return customerService.addCustomer(customer);
    // }

    @PostMapping("/addCustomer")
    public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
        // String result = customerService.addCustomer(customer);
        // return ResponseEntity.status(HttpStatus.CREATED).body(result);
        try {
            String result = customerService.addCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (CustomerAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (DatabaseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    //update customer
    @PutMapping("updateCustomer/{id}")
    public String updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        return customerService.updateCustomer(id, customer);
    }
}
