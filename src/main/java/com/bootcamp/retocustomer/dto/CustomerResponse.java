package com.bootcamp.retocustomer.dto;

public class CustomerResponse {

    private String dni;
    private String fullname;
    private Integer age;

    public CustomerResponse() {
    }

    public CustomerResponse(String dni, String fullname, Integer age) {
        this.dni = dni;
        this.fullname = fullname;
        this.age = age;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
