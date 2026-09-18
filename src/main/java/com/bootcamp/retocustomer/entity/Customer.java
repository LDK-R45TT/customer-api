package com.bootcamp.retocustomer.entity;

import com.bootcamp.retocustomer.dto.CustomerResponse;
import jakarta.persistence.*;


@Entity
@Table(name = "clientes")
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dni;
    private String name;
    private String lastname;
    private Integer age;
    private Boolean active=true;
    public Customer() {
    }

    public Customer(String dni, String name, String lastname, Integer age) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public CustomerResponse toDto() {
        return
                new CustomerResponse(dni,
                        String.format("%s %s", name, lastname),
                        age, active);
    }
    public void actualizarCliente(Customer clienteNuevo){
        this.setName(clienteNuevo.getName());
        this.setLastname(clienteNuevo.getLastname());
        this.setDni(clienteNuevo.getDni());
        this.setAge(clienteNuevo.getAge());
        this.setActive(clienteNuevo.getActive());
    }

    public Long getId() {
        return id;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
