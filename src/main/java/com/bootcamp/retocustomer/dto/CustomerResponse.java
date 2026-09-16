package com.bootcamp.retocustomer.dto;

public class CustomerResponse {

    private String dni;
    private String fullname;
    private Integer age;
    private Boolean active;
    public CustomerResponse() {
    }


    public CustomerResponse(String dni, String fullname, Integer age, Boolean active) {
        this.dni = dni;
        this.fullname = fullname;
        this.age = age;
        this.active = active;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
