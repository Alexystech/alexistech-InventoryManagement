package com.itsx.alexis.service.impl;

import com.itsx.alexis.entity.Administrator;
import com.itsx.alexis.repository.AdministratorRepository;
import com.itsx.alexis.service.AdministratorService;
import com.itsx.alexis.service.exception.AdministratorIsNullException;
import com.itsx.alexis.service.exception.AdministratorNotFoundException;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    private final AdministratorRepository administratorRepo;

    @Autowired
    public AdministratorServiceImpl(AdministratorRepository administratorRepo) {
        this.administratorRepo = administratorRepo;
    }

    @Override
    public Administrator createAdministrator(Administrator administrator) {

        if ( administrator == null ) {
            throw AdministratorIsNullException.of();
        }

        return administratorRepo.save(administrator);
    }

    @Override
    public void deleteAdministratorById(long idAdministrator) {

        if ( idAdministrator < 1 ) {
            throw AdministratorIsNullException.of();
        }

        Try.of( () -> getAdministratorById(idAdministrator) )
                .onFailure( (exception) -> {
                    throw AdministratorNotFoundException.of(idAdministrator);
                });

        administratorRepo.deleteById(idAdministrator);
    }

    @Override
    public Administrator updateAdministrator(Administrator administrator) {

        if ( administrator == null ) {
            throw AdministratorIsNullException.of();
        }

        return administratorRepo.save(administrator);
    }

    @Override
    public Administrator getAdministratorById(long idAdministrator) {
        return administratorRepo.findById(idAdministrator)
                .orElseThrow(() -> {
                    throw AdministratorNotFoundException.of(idAdministrator);
                });
    }

    @Override
    public List<Administrator> getAllAdministrators() {
        return ((List<Administrator>) administratorRepo.findAll());
    }
}
