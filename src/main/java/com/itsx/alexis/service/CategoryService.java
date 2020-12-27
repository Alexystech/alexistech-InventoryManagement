package com.itsx.alexis.service;

import com.itsx.alexis.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void createCategory(Category category);

    Optional<Category> findById(int id);

    void deleteCategory(int id);

    List<Category> findAll();
}
