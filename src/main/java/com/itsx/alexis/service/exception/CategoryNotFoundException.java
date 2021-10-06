package com.itsx.alexis.service.exception;

public class CategoryNotFoundException extends RuntimeException{

    public static CategoryNotFoundException of(long idCategory) {
        return new CategoryNotFoundException(idCategory);
    }

    public CategoryNotFoundException(long idCategory) {
        super(String.format("category with id %d was not found", idCategory));
    }

}
