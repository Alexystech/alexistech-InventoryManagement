package com.itsx.alexis.repository;

import com.itsx.alexis.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(value = "SELECT c FROM Category c WHERE c.idCategory =: idCategory")
    Optional<List<Product>> findAllProductsByCategory(@Param("idCategory") long idCategory);

    @Query(value = "SELECT s FROM Supplier s WHERE s.idSupplier =: idSupplier")
    Optional<List<Product>> findAllProductsBySupplier(@Param("idSupplier") long idSupplier);

}
