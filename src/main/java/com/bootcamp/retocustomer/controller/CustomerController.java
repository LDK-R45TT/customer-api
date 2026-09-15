package com.bootcamp.retocustomer.controller;

import com.bootcamp.retocustomer.dto.CustomerRequest;
import com.bootcamp.retocustomer.dto.CustomerResponse;
import com.bootcamp.retocustomer.entity.Customer;
import com.bootcamp.retocustomer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //1-obtener todos
    @GetMapping("/all")
    public List<Customer> getAll(){
        return customerService.getAll();
    }


    //2-obtener por id
    @GetMapping("/{id}")
    public Customer getById(@PathVariable("id") Long clientId){
        return customerService.getById(clientId);
    }
    //3-crear nuevo
    @PostMapping("/")
    public ResponseEntity<CustomerResponse> insertOne(@Valid @RequestBody CustomerRequest request){
        Customer newCustomer = new Customer();
        newCustomer.setName(request.getName());
        newCustomer.setLastname(request.getLastname());
        newCustomer.setDni(request.getDni());
        newCustomer.setAge(request.getAge());
        customerService.save(newCustomer);
        CustomerResponse response = new CustomerResponse(newCustomer.getDni(), String.format("%s %s", newCustomer.getName(), newCustomer.getLastname()),newCustomer.getAge());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    //4-eliminar por id
    @DeleteMapping("/delete/{id}")
    public void deleteOne(@PathVariable("id") Long clientId){
        customerService.delete(clientId);
    }
    //deletear todos
    @DeleteMapping("/delete-all")
    public void deleteAll(){
        customerService.deleteAll();
    }
    //agregar todos
    @PostMapping("/all")
    public List<Customer> addAll(@RequestBody List<Customer> customers){
        customerService.insertAll(customers);
        return customers;
    }


}
