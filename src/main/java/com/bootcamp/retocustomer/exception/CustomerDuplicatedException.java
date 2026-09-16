package com.bootcamp.retocustomer.exception;

public class CustomerDuplicatedException extends RuntimeException{

    public CustomerDuplicatedException(String message){
        super(message);
    }
}
