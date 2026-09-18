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
    public ResponseEntity<List<CustomerResponse>> getAll(){
        List<CustomerResponse> response = customerService.getAll().stream().map(it->it.toDto()).toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    //2-obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable("id") Long clientId){
        CustomerResponse response =customerService.getById(clientId).toDto();
        return ResponseEntity.status(HttpStatus.OK).body(response);
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
        CustomerResponse response = newCustomer.toDto();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    //4-eliminar por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable("id") Long clientId){
        customerService.delete(clientId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    //5-obtener por nombre
    @GetMapping("/por-nombre")
    public ResponseEntity<CustomerResponse> obtenerPorNombre(@RequestParam String name){
            CustomerResponse response = customerService.getByName(name).toDto();
        return  ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
    //6-deletear todos
    @DeleteMapping("/delete-all")
    public ResponseEntity<?> deleteAll(){
        customerService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    //7-agregar todos
    @PostMapping("/all")
    public ResponseEntity<List<CustomerResponse>> addAll(@Valid @RequestBody List<CustomerRequest> customers){
        List<Customer> customersEntity = customers.stream().map(it->it.toCustomer()).toList();
        customerService.insertAll(customersEntity);
        List<CustomerResponse> response =customersEntity.stream().map(it->it.toDto()).toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //8-actualizar porid
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CustomerResponse> actualizaCliente(@Valid @PathVariable("id") Long idCliente, @RequestBody CustomerRequest clienteActualizar){
        Customer customer = clienteActualizar.toCustomer();
        customerService.update(customer, idCliente);
        return ResponseEntity.status(HttpStatus.OK).body(customer.toDto());
    }




}
