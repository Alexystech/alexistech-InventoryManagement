package com.itsx.alexis.service;

import com.itsx.alexis.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void createProduct(Product product);
    Optional<Product>findById(int id);
    void removeProduct(int id);
    List<Product>findAll();
}
