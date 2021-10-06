package com.itsx.alexis.service;

import com.itsx.alexis.entity.Supplier;

import java.util.List;

public interface SupplierService {
    Supplier createSupplier(Supplier supplier);
    List<Supplier> createAPullOfSuppliers(List<Supplier> suppliers);
    void deleteSupplierById(long idSupplier);
    Supplier updateSupplier(Supplier supplier);
    Supplier getSupplierById(long idSupplier);
    List<Supplier> getAllSuppliers();
}
