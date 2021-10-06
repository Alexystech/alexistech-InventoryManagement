package com.itsx.alexis.service.exception;

public class CategoryIsNullException extends RuntimeException{

    public CategoryIsNullException of() {
        return new CategoryIsNullException();
    }

    public CategoryIsNullException() {
        super("category that you try to send is null");
    }

}
