package com.bootcamp.retocustomer;

import com.bootcamp.retocustomer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Customer findByDni(String dni);
    Customer findFirstByDni(String dni);
    Customer findFirstByName(String name);
}
