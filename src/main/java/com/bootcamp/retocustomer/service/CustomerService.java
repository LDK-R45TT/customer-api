package com.bootcamp.retocustomer.service;

import com.bootcamp.retocustomer.CustomerRepository;
import com.bootcamp.retocustomer.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class CustomerService {


    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    /**
     * agrega listado completo customers
     * @param customerList  listado de customers.
     * */
    public void insertAll(List<Customer>customerList){
        customerList.forEach(it->{
            Customer clienteEncontrado = repository.findByDni(it.getDni());
            if(clienteEncontrado != null){
                throw new RuntimeException(String.format("cliente con dni %s ya existe", it.getDni()));
            }
        });
        repository.saveAll(customerList);
    }

    /**
     * obtener listado
     * @return  retorna list de customers
     */
    public List<Customer> getAll(){
        return repository.findAll();
    }
    /**
     * obtener por id
     * @param id  identificador customer
     * @return  Customer filtrado
     * */
    public Customer getById(Long id){
        return repository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
    /**
     * agregar customer en lista
     * @param newCustomer  customer
     * @return  customer agregado
     */

    public Customer save(Customer newCustomer){
        this.repository.save(newCustomer);
        return newCustomer;
    }
    /**
     *elimina por id
     * @param id -> identificador customer
     */
    public void delete(Long id){

        Customer cus = this.getById(id);
        repository.delete(cus);
    }
    /**
     * limpia listado de customer
     * */
    public void deleteAll(){
        repository.deleteAll();
    }



}
