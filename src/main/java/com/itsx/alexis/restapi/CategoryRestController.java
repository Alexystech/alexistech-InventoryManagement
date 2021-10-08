package com.itsx.alexis.restapi;

import com.itsx.alexis.entity.Category;
import com.itsx.alexis.service.CategoryService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiResponses({
            @ApiResponse(code = 201, message = "category was been created"),
            @ApiResponse(code = 422, message = "category is null, unprocessable entity")
    })
    @PostMapping(path = "/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.createCategory(category)
                , HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(code = 201, message = "all categories was been created"),
            @ApiResponse(code = 422, message = "some objects are invalid to complete the transaction")
    })
    @PostMapping(path = "/create/pull")
    public ResponseEntity<List<Category>> createAPullOfCategories(@RequestBody List<Category> categories) {
        return new ResponseEntity<>(categoryService.createAPullOfCategories(categories)
                , HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(code = 201, message = "id accepted, category was been deleted"),
            @ApiResponse(code = 404, message = "category was not found"),
            @ApiResponse(code = 422, message = "id out of bound, your id is minor to 1")
    })
    @DeleteMapping(path = "/delete/{idCategory}")
    public ResponseEntity<Boolean> deleteCategoryById(@PathVariable long idCategory) {
        categoryService.deleteCategoryById(idCategory);
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }

    @ApiResponses({
            @ApiResponse(code = 201, message = "category was updated"),
            @ApiResponse(code = 422, message = "category is null")
    })
    @PutMapping(path = "/update")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.updateCategory(category)
                , HttpStatus.ACCEPTED);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "category was returned"),
            @ApiResponse(code = 404, message = "category was not found")
    })
    @GetMapping(path = "/get/{idCategory}")
    public ResponseEntity<Category> getCategoryById(@PathVariable long idCategory) {
        return new ResponseEntity<>(categoryService.getCategoryById(idCategory)
                , HttpStatus.OK);
    }

    @ApiResponse(code = 200, message = "all categories was returned")
    @GetMapping(path = "/get/all")
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories()
                , HttpStatus.OK);
    }

}
