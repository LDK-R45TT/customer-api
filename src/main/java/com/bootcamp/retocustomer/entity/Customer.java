package com.bootcamp.retocustomer.entity;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class Customer {

    private static Long idCounter = 0L;

    private Long id = 0L;
    private String dni;
    private String name;
    private String lastname;
    private Integer age;

    public Customer() {
    }

    public Customer(String dni, String name, String lastname, Integer age) {
        this.id = ++idCounter;
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
