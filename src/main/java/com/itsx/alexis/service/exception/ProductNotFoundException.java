package com.itsx.alexis.service.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException of(long idProduct) {
        return new ProductNotFoundException(idProduct);
    }

    public ProductNotFoundException(long idProdcut) {
        super(String.format("Product with id %d was not found", idProdcut));
    }

}
