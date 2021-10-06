package com.itsx.alexis.service.exception;

public class AdministratorNotFoundException extends RuntimeException{

    public AdministratorNotFoundException of(long idAdministrator) {
        return new AdministratorNotFoundException(idAdministrator);
    }

    public AdministratorNotFoundException(long idAdministrator) {
        super(String.format("administrator with id %d was not found"
                , idAdministrator));
    }

}
