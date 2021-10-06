package com.itsx.alexis.repository;

import com.itsx.alexis.entity.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends CrudRepository<Long, Supplier> {
}
