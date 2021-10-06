package com.itsx.alexis.service.exception;

public class SupplierIsNullException extends RuntimeException{

    public static SupplierIsNullException of() {
        return new SupplierIsNullException();
    }

    public SupplierIsNullException() {
        super("supplier that you try to send is null");
    }

}
