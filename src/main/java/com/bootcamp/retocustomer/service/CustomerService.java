package com.bootcamp.retocustomer.service;

import com.bootcamp.retocustomer.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class CustomerService {

    private final ArrayList<Customer> customers = new ArrayList<>(List.of());


    /**
     * crear un nuevo customer
     * @param customerList  listado de customers.
     * */
    public void insertAll(List<Customer>customerList){
        customerList.forEach(it->customers.add(it));
    }

    /**
     * obtener listado
     * @return  retorna list de customers
     */
    public List<Customer> getAll(){
        return customers;
    }
    /**
     * obtener por id
     * @param id  identificador customer
     * @return  Customer filtrado
     * */
    public Customer getById(Long id){
        return customers.stream().filter(it->it.getId().equals(id))
                .findFirst().orElseThrow(()-> new NoSuchElementException("customer not found"));
    }
    /**
     * agregar customer en lista
     * @param newCustomer  customer
     * @return  customer agregado
     */

    public Customer save(Customer newCustomer){
        customers.add(newCustomer);
        return newCustomer;
    }
    /**
     *elimina por id
     * @param id -> identificador customer
     */
    public void delete(Long id){
        customers.removeIf(it->it.getId().equals(id));
    }
    /**
     * limpia listado de customer
     * */
    public void deleteAll(){
        customers.clear();
    }
    /**
     * agrega listado completo customers
     * @param customerList -> lista de customer
     */
    public void addAll(List<Customer> customerList){
        customers.addAll(customerList);

    }

}
