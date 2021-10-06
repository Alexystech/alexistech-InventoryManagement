package com.itsx.alexis.service;

import com.itsx.alexis.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> createAPullOfProducts(List<Product> products);
    void deleteProduct(long idProduct);
    Product updateProduct(Product product);
    Product getProductById(long idProduct);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(long idCategory);
    List<Product> getProductsBySupplier(long idSupplier);
}
