package com.bootcamp.retocustomer.dto;

import com.bootcamp.retocustomer.entity.Customer;
import jakarta.validation.constraints.*;


public class CustomerRequest {
    @NotBlank(message = "El DNI no puede estar vacío")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 dígitos")
    @Pattern(regexp = "^[0-9]+$", message = "El DNI debe contener solo números")
    private String dni;
    @NotBlank(message = "el nombre no puede estar vacio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String name;
    @NotBlank(message = "el apellido no puede estar vacio")
    private String lastname;
    @NotNull
    @Min(value = 18, message = "El cliente debe ser mayor de edad (mínimo 18 años)")
    @Max(value = 70, message = "el cliente debe tener maximo 70 años")
    private Integer age;


    public CustomerRequest() {
    }

    public CustomerRequest(String dni, String name, String lastname, Integer age) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public Customer toCustomer(){
        return new Customer(dni, name,lastname, age);
    }
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}
