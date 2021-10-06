package com.itsx.alexis.service.exception;

public class AdministratorIsNullException extends RuntimeException{

    public static AdministratorIsNullException of() {
        return new AdministratorIsNullException();
    }

    public AdministratorIsNullException() {
        super("administrator that you try to send is null");
    }

}
