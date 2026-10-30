package com.mycompany.group5.exception;

public class SaleValidationException extends RuntimeException {
    public SaleValidationException(String message){
        super(message);
    }
}