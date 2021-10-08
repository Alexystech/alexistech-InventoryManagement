package com.itsx.alexis.service.exception;

public class CategoryTransactionException extends RuntimeException{

    public static CategoryTransactionException of() {
        return new CategoryTransactionException();
    }

    private CategoryTransactionException() {
        super("some objects are invalid to complete the transaction");
    }

}
