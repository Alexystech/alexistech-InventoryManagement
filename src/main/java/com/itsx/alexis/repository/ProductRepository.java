package com.itsx.alexis.repository;

import com.itsx.alexis.entity.Product;
import com.itsx.alexis.entity.Supplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(value = "SELECT c FROM Category c WHERE c.idCategory =: idCategory")
    List<Product> findAllProductsByCategory(@Param("idCategory") long idCategory);

    @Query(value = "SELECT s FROM Suplier s WHERE s.idSupplier =: idSupplier")
    List<Supplier> findAllProductsBySupplier(@Param("idSupplier") long idSupplier);

}
