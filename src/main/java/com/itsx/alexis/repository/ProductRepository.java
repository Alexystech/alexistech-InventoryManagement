package com.itsx.alexis.repository;

import com.itsx.alexis.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Long, Product> {
}
