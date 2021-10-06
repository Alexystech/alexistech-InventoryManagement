package com.itsx.alexis.service;

import com.itsx.alexis.entity.Administrator;

import java.util.List;

public interface AdministratorService {
    Administrator createAdministrator(Administrator administrator);
    void deleteAdministratorById(long idAdministrator);
    Administrator updateAdministrator(Administrator administrator);
    Administrator getAdministratorById(long idAdministrator);
    List<Administrator> getAllAdministrators();
}
