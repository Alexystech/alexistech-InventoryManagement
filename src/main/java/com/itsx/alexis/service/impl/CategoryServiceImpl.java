package com.itsx.alexis.service.impl;

import com.itsx.alexis.entity.Category;
import com.itsx.alexis.repository.CategoryRepository;
import com.itsx.alexis.service.CategoryService;
import com.itsx.alexis.service.exception.CategoryTransactionException;
import com.itsx.alexis.service.exception.CategoryIsNullException;
import com.itsx.alexis.service.exception.CategoryNotFoundException;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepo;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Category createCategory(Category category) {

        if ( category == null ) {
            throw CategoryIsNullException.of();
        }

        return categoryRepo.save(category);
    }

    @Transactional
    @Override
    public List<Category> createAPullOfCategories(List<Category> categories) {

        Try<List<Category>> responseCategories = createAllCategoriesAndGetResponseCategories(categories);

        return responseCategories.get();
    }

    private Try<List<Category>> createAllCategoriesAndGetResponseCategories(List<Category> categories) {
        return Try.of( () -> (List<Category>) categoryRepo.saveAll(categories))
                .onFailure( (exception) -> {
                    throw CategoryTransactionException.of();
                });
    }

    @Override
    public void deleteCategoryById(long idCategory) {

        if ( idCategory < 1 ) {
            throw CategoryIsNullException.of();
        }

        Try.of( () -> getCategoryById(idCategory) ).onFailure( (exception) -> {
            throw CategoryNotFoundException.of(idCategory);
        });

        categoryRepo.deleteById(idCategory);
    }

    @Override
    public Category updateCategory(Category category) {

        if ( category == null ) {
            throw CategoryIsNullException.of();
        }

        return categoryRepo.save(category);
    }

    @Override
    public Category getCategoryById(long idCategory) {
        return categoryRepo.findById(idCategory).orElseThrow( () -> {
            throw CategoryNotFoundException.of(idCategory);
        });
    }

    @Override
    public List<Category> getAllCategories() {
        return ((List<Category>) categoryRepo.findAll());
    }
}
