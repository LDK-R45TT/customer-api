package com.bootcamp.retocustomer.dto;

import jakarta.validation.constraints.*;

public class CustomerRequest {
    @NotBlank(message = "El DNI no puede estar vacío")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 dígitos")
    @NotNull
    private String dni;
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String name;
    @NotNull
    private String lastname;
    @Positive
    @NotNull
    @Max(value = 90, message = "el cliente debe tener maximo 90 años")
    private Integer age;

    public CustomerRequest() {
    }

    public CustomerRequest(String dni, String name, String lastname, Integer age) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
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
