package com.itsx.alexis.service.exception;

public class ProductTransactionException extends RuntimeException{

    public static ProductTransactionException of() {
        return new ProductTransactionException();
    }

    public ProductTransactionException() {
        super("some objects are invalid to complete the transaction");
    }

}
