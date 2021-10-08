package com.itsx.alexis.service.impl;

import com.itsx.alexis.entity.Supplier;
import com.itsx.alexis.repository.SupplierRepository;
import com.itsx.alexis.service.SupplierService;
import com.itsx.alexis.service.exception.SupplierIsNullException;
import com.itsx.alexis.service.exception.SupplierNotFoundException;
import com.itsx.alexis.service.exception.SupplierTransactionException;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    public final SupplierRepository supplierRepo;
    
    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepo) {
        this.supplierRepo = supplierRepo;
    }
    
    @Override
    public Supplier createSupplier(Supplier supplier) {
        
        if ( supplier == null ) {
            throw SupplierIsNullException.of();
        }
        
        return supplierRepo.save(supplier);
    }

    @Transactional
    @Override
    public List<Supplier> createAPullOfSuppliers(List<Supplier> suppliers) {

        Try<List<Supplier>> responseSuppliers = createAllSuppliersAndGetResponseSuppliers(suppliers);

        return responseSuppliers.get();
    }

    private Try<List<Supplier>> createAllSuppliersAndGetResponseSuppliers(List<Supplier> suppliers) {
        return Try.of( () -> (List<Supplier>) supplierRepo.saveAll(suppliers) ).onFailure( (exception) -> {
            throw SupplierTransactionException.of();
        });
    }

    @Override
    public void deleteSupplierById(long idSupplier) {
        
        if ( idSupplier < 1 ) {
            throw SupplierIsNullException.of();
        }

        Try.of( () -> getSupplierById(idSupplier) ).onFailure( (exception) -> {
            throw SupplierNotFoundException.of(idSupplier);
        });
        
        supplierRepo.deleteById(idSupplier);
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        
        if ( supplier == null ) {
            throw SupplierIsNullException.of();
        }
        
        return supplierRepo.save(supplier);
    }

    @Override
    public Supplier getSupplierById(long idSupplier) {
        return supplierRepo.findById(idSupplier).orElseThrow( () -> {
            throw SupplierNotFoundException.of(idSupplier);
        });
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return ((List<Supplier>) supplierRepo.findAll());
    }
}
