package com.itsx.alexis.service.exception;

public class SupplierNotFoundException extends RuntimeException{

    public static SupplierNotFoundException of(long idSupplier) {
        return new SupplierNotFoundException(idSupplier);
    }

    public SupplierNotFoundException(long idSupplier) {
        super(String.format("supplier with id %d was not found"
                , idSupplier));
    }

}
