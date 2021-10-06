package com.itsx.alexis.service.exception;

public class ProductIsNullException extends RuntimeException{

    public ProductIsNullException of() {
        return new ProductIsNullException();
    }

    public ProductIsNullException() {
        super("product that you try to send is null");
    }

}
