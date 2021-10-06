package com.itsx.alexis.service;

import com.itsx.alexis.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    List<Category> createAPullOfCategories(List<Category> categories);
    void deleteCategoryById(long idCategory);
    Category updateCategory(Category category);
    Category getCategoryById(long idCategory);
    List<Category> getAllCategories();
}
