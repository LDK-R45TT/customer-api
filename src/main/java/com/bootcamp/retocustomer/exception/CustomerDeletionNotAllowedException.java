package com.bootcamp.retocustomer.exception;

public class CustomerDeletionNotAllowedException extends RuntimeException{

    public CustomerDeletionNotAllowedException(String message){
        super(message);
    }
}
