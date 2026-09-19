package com.bootcamp.retocustomer.controller;

import com.bootcamp.retocustomer.dto.CustomerRequest;
import com.bootcamp.retocustomer.dto.CustomerResponse;
import com.bootcamp.retocustomer.entity.Customer;
import com.bootcamp.retocustomer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customers")
@Tag(name = "Clientes", description =  "Crud de Clientes")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //1-obtener todos
    @GetMapping("/all")
    @Operation(summary = "Lista todos los Clientes", description = "Se obtienen todos los clientes del sistema")
    @ApiResponse(responseCode = "200", description = "Operación exitosa!")
    public ResponseEntity<List<CustomerResponse>> getAll(){
        List<CustomerResponse> response = customerService.getAll().stream()
                .map(it->it.toDto()).toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    //2-obtener por id
    @GetMapping("/{id}")
    @Operation(summary = "Obtiene Cliente", description = "Se busca un cliente por id en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente localizado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<CustomerResponse> getById(@PathVariable("id") Long clientId){
        CustomerResponse response =customerService.getById(clientId).toDto();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    //3-crear nuevo
    @PostMapping("/")
    @Operation(summary = "Guarda Cliente", description = "Se guarda un cliente nuevo en la DB.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente creado con exito"),
            @ApiResponse(responseCode = "500", description = "No se pudo guardar el cliente debido a errores en la validación")
    })
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
    @Operation(summary = "Elimina Cliente", description = "Se elimina un cliente por id de la DB.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente eliminado con exito!"),
            @ApiResponse(responseCode = "404", description = "Cliente no existe en el sistema")
    })
    public ResponseEntity<?> deleteOne(@PathVariable("id") Long clientId){
        customerService.delete(clientId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    //5-obtener por nombre
    @GetMapping("/por-nombre")
    @Operation(summary = "Obtiene Clientes por nombre", description = "Se filtran los clientes por nombre.")
    @ApiResponses({
            @ApiResponse(responseCode = "302", description = "Busqueda exitosa"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<CustomerResponse> obtenerPorNombre(@RequestParam String name){
            CustomerResponse response = customerService.getByName(name).toDto();
        return  ResponseEntity.status(HttpStatus.FOUND).body(response);
    }
    //6-deletear todos
    @DeleteMapping("/delete-all")
    @Operation(summary = "Elimina todos", description = "Se eliminan todos los registros de la tabla clientes.")
    @ApiResponse(responseCode = "200", description = "Se eliminan todos los clientes")
    public ResponseEntity<?> deleteAll(){
        customerService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    //7-agregar todos
    @PostMapping("/all")
    @Operation(summary = "Guarda todos", description = "Se guarda todo el listado de clientes en la DB.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Todo agregado"),
            @ApiResponse(responseCode = "400", description = "Problemas en guardar")
    })
    public ResponseEntity<List<CustomerResponse>> addAll( @RequestBody List<CustomerRequest> customers){
        List<Customer> customersEntity = customers.stream().map(it->it.toCustomer()).toList();
        customerService.insertAll(customersEntity);
        List<CustomerResponse> response =customersEntity.stream().map(it->it.toDto()).toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //8-actualizar porid
    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualiza Cliente", description = "Se actualiza un cliente en el sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Actualización completada de forma exitosa."),
            @ApiResponse(responseCode = "404", description = "Cliente no existe.")
    })
    public ResponseEntity<CustomerResponse> actualizaCliente(@Valid @PathVariable("id") Long idCliente, @RequestBody CustomerRequest clienteActualizar){
        Customer customer = clienteActualizar.toCustomer();
        customerService.update(customer, idCliente);
        return ResponseEntity.status(HttpStatus.OK).body(customer.toDto());
    }




}
