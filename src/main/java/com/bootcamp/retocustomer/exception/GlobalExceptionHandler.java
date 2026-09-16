package com.bootcamp.retocustomer.exception;

import com.bootcamp.retocustomer.errors.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class GlobalExceptionHandler {
    /*exception campos validation*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(fe->errors.put(fe.getField(), fe.getDefaultMessage()));
        ErrorResponse error = new ErrorResponse(
                400, "Datos Invalidos", LocalDateTime.now(), errors
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    /*exception customer no encontrado*/
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleValidation(CustomerNotFoundException ex){
        ErrorResponse err = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    /*exception deletion customer*/
    @ExceptionHandler(CustomerDeletionNotAllowedException.class)
    public ResponseEntity<ErrorResponse> handleValidation(CustomerDeletionNotAllowedException ex){
        ErrorResponse err = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    /*exception duplicated customer*/
    @ExceptionHandler(CustomerDuplicatedException.class)
    public ResponseEntity<ErrorResponse> handleValidation(CustomerDuplicatedException ex){
        ErrorResponse err = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

}
