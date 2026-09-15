package com.bootcamp.retocustomer.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CustomerRequest {
    @Max(value = 8, message = "el dni debe tener 8 digitos")
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
}
