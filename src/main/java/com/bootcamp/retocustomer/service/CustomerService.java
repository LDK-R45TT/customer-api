package com.bootcamp.retocustomer.service;

import com.bootcamp.retocustomer.CustomerRepository;
import com.bootcamp.retocustomer.entity.Customer;
import com.bootcamp.retocustomer.exception.CustomerDeletionNotAllowedException;
import com.bootcamp.retocustomer.exception.CustomerDuplicatedException;
import com.bootcamp.retocustomer.exception.CustomerNotFoundException;
import org.springframework.stereotype.Service;
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
            Customer clienteEncontrado = repository.findFirstByDni(it.getDni());
            if(clienteEncontrado != null){
                throw new CustomerDuplicatedException
                        (String.format("cliente con dni %s ya existe", it.getDni()));
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
        Customer clienteEncontrado = repository.findFirstByDni(newCustomer.getDni());
        if(clienteEncontrado != null){
            throw new CustomerDuplicatedException(String.format("cliente con dni %s ya existe", clienteEncontrado.getDni()));
        }
        repository.save(newCustomer);
        return newCustomer;
    }
    /**
     *elimina por id
     * @param id -> identificador customer
     */
    public void delete(Long id){

        Customer cus = getById(id);
        if(cus.getActive()){
            throw new CustomerDeletionNotAllowedException
                    ("customer "+cus.getName()+
                            " no se puede eliminar porque esta activo.");
        }
        repository.delete(cus);
    }
    /**
     * obtener nombre
     * @param name nombre customer*/
    public Customer getByName(String name) {
        Customer clienteEncontrado = repository.findFirstByName(name);
        if(clienteEncontrado == null){
           throw new CustomerNotFoundException("el cliente con nombre "+name+" no existe");
        }
        return clienteEncontrado;
    }
    /**
     * actualizar customer
     * @param customerUpdate customer
     * @param id identificador customer*/
    public Customer update(Customer customerUpdate, Long id) {
        Customer clienteEncontrado = getById(id);
        //actualizacion atributos
        clienteEncontrado.actualizarCliente(customerUpdate);
        return repository.save(clienteEncontrado);
    }

    /**
     * limpia listado de customer
     * */
    public void deleteAll(){
        repository.deleteAll();
    }



}
