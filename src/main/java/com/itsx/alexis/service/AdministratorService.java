package com.itsx.alexis.service;

import com.itsx.alexis.entity.Administrator;

import java.util.List;
import java.util.Optional;

public interface AdministratorService {
    void createAdministrator(Administrator administrator);

    Optional<Administrator> findById(int id);

    void deleteAdministrator(int id);

    List<Administrator> findAll();
}
