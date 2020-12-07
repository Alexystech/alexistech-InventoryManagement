package com.itsx.alexis.utility;

import com.itsx.alexis.entity.Category;
import com.itsx.alexis.entity.Product;
import com.itsx.alexis.entity.Supplier;

import java.util.List;

public class Validation {

    public boolean haveAnyProductByCategory(List<Product>products, Category category) {
        boolean definition = false;
        for (Product product : products) {
            if (product.getCategory().getIdCategory() == category.getIdCategory()) {
                definition = true;
                break;
            }
        }
        return definition;
    }

    public boolean haveAnyProductBySupplier(List<Product>products, Supplier supplier) {
        boolean definition = false;
        for (Product product : products) {
            if (product.getSupplier().getIdSupplier() == supplier.getIdSupplier()) {
                definition = true;
                break;
            }
        }
        return definition;
    }
}
