package com.itsx.alexis.service.exception;

public class ProductIsNullException extends RuntimeException{

    public static ProductIsNullException of() {
        return new ProductIsNullException();
    }

    public ProductIsNullException() {
        super("product that you try to send is null");
    }

}
