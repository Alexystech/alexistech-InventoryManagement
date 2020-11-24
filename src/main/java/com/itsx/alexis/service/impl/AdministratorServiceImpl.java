package com.itsx.alexis.service.impl;

import com.itsx.alexis.entity.Administrator;
import com.itsx.alexis.repository.AdministratorRepository;
import com.itsx.alexis.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public void createAdministrator(Administrator administrator) {
        administratorRepository.save(administrator);
    }

    @Override
    public Optional<Administrator> findById(int id) {
        return administratorRepository.findById(id);
    }

    @Override
    public void deleteAdministrator(int id) {
        administratorRepository.deleteById(id);
    }

    @Override
    public List<Administrator> findAll() {
        return administratorRepository.findAll();
    }
}
