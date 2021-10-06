package com.itsx.alexis.service.impl;

import com.itsx.alexis.entity.Product;
import com.itsx.alexis.repository.ProductRepository;
import com.itsx.alexis.service.ProductService;
import com.itsx.alexis.service.exception.CategoryIsNullException;
import com.itsx.alexis.service.exception.CategoryNotFoundException;
import com.itsx.alexis.service.exception.ProductIsNullException;
import com.itsx.alexis.service.exception.ProductNotFoundException;
import com.itsx.alexis.service.exception.SupplierIsNullException;
import com.itsx.alexis.service.exception.SupplierNotFoundException;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product createProduct(Product product) {

        if ( product == null ) {
            throw ProductIsNullException.of();
        }

        return productRepo.save(product);
    }

    @Transactional
    @Override
    public List<Product> createAPullOfProducts(List<Product> products) {
        return ((List<Product>) productRepo.saveAll(products));
    }

    @Override
    public void deleteProduct(long idProduct) {

        if ( idProduct < 1 ) {
            throw ProductIsNullException.of();
        }

        Try.of( () -> getProductById(idProduct) ).onFailure( (exception) -> {
            throw ProductNotFoundException.of(idProduct);
        });

        productRepo.deleteById(idProduct);
    }

    @Override
    public Product updateProduct(Product product) {

        if ( product == null ) {
            throw ProductIsNullException.of();
        }

        return productRepo.save(product);
    }

    @Override
    public Product getProductById(long idProduct) {
        return productRepo.findById(idProduct).orElseThrow( () -> {
            throw ProductNotFoundException.of(idProduct);
        });
    }

    @Override
    public List<Product> getAllProducts() {
        return ((List<Product>) productRepo.findAll());
    }

    @Override
    public List<Product> getProductsByCategory(long idCategory) {

        if ( idCategory < 1 ) {
            throw CategoryIsNullException.of();
        }

        return productRepo.findAllProductsByCategory(idCategory).orElseThrow( () -> {
            throw CategoryNotFoundException.of(idCategory);
        });
    }

    @Override
    public List<Product> getProductsBySupplier(long idSupplier) {

        if ( idSupplier < 1 ) {
            throw SupplierIsNullException.of();
        }

        return productRepo.findAllProductsBySupplier(idSupplier).orElseThrow( () -> {
            throw SupplierNotFoundException.of(idSupplier);
        });
    }
}
