package com.bootcamp.retocustomer.controller;

import com.bootcamp.retocustomer.entity.Customer;
import com.bootcamp.retocustomer.service.CustomerService;
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
    public Customer insertOne(@RequestBody Customer newCustomer){
        return customerService.save(newCustomer);
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
