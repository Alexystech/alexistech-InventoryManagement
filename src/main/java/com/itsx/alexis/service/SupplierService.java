package com.itsx.alexis.service;

import com.itsx.alexis.entity.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    void createSupplier(Supplier supplier);

    Optional<Supplier> findById(int id);

    void removeSupplier(int id);

    List<Supplier> findAll();
}
