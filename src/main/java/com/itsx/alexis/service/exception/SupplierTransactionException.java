package com.itsx.alexis.service.exception;

public class SupplierTransactionException extends RuntimeException{

    public static SupplierTransactionException of() {
        return new SupplierTransactionException();
    }

    public SupplierTransactionException() {
        super("some objects are invalid to complete the transaction");
    }

}
