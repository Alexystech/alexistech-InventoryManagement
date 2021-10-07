package com.itsx.alexis.restapi;

import com.itsx.alexis.entity.Category;
import com.itsx.alexis.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryRestController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.createCategory(category)
                , HttpStatus.CREATED);
    }

    @PostMapping(path = "/create/pull")
    public ResponseEntity<List<Category>> createAPullOfCategories(@RequestBody List<Category> categories) {
        return new ResponseEntity<>(categoryService.createAPullOfCategories(categories)
                , HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{idCategory}")
    public ResponseEntity<Boolean> deleteCategoryById(@PathVariable long idCategory) {
        categoryService.deleteCategoryById(idCategory);
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.updateCategory(category)
                , HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/get/{idCategory}")
    public ResponseEntity<Category> getCategoryById(@PathVariable long idCategory) {
        return new ResponseEntity<>(categoryService.getCategoryById(idCategory)
                , HttpStatus.OK);
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories()
                , HttpStatus.OK);
    }

}
