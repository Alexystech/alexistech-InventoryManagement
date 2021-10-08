package com.itsx.alexis.restapi;

import com.itsx.alexis.entity.Product;
import com.itsx.alexis.service.ProductService;
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
@RequestMapping(path = "/product")
public class ProductRestController {

    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @ApiResponses({
            @ApiResponse(code = 201, message = "product was been created"),
            @ApiResponse(code = 422, message = "product is null, unprocessable entity")
    })
    @PostMapping(path = "/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.createProduct(product)
                , HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(code = 201, message = "all products was been created"),
    })
    @PostMapping(path = "/create/pull")
    public ResponseEntity<List<Product>> createAPullOfProducts(@RequestBody List<Product> products) {
        return new ResponseEntity<>(productService.createAPullOfProducts(products)
                , HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(code = 201, message = "id accepted, product was been deleted"),
            @ApiResponse(code = 404, message = "product was not found"),
            @ApiResponse(code = 422, message = "id out of bound, your id is minor to 1")
    })
    @DeleteMapping(path = "/delete/{idProduct}")
    public ResponseEntity<Boolean> deleteProductById(@PathVariable long idProduct) {
        productService.deleteProduct(idProduct);
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }

    @ApiResponses({
            @ApiResponse(code = 201, message = "product was updated"),
            @ApiResponse(code = 422, message = "product is null")
    })
    @PutMapping(path = "/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(product)
                , HttpStatus.ACCEPTED);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "product was returned"),
            @ApiResponse(code = 404, message = "product was not found")
    })
    @GetMapping(path = "/get/{idProduct}")
    public ResponseEntity<Product> getProductById(@PathVariable long idProduct) {
        return new ResponseEntity<>(productService.getProductById(idProduct)
                , HttpStatus.OK);
    }

    @ApiResponse(code = 200, message = "all products was returned")
    @GetMapping(path = "/get/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "all products was been filtered and returned by category")
    })
    @GetMapping(path = "/get/by/category/{idCategory}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable long idCategory) {
        return new ResponseEntity<>(productService.getProductsByCategory(idCategory)
                , HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "all prodcuts was been filtered and returned by supplier")
    })
    @GetMapping(path = "/get/by/supplier/{idSupplier}")
    public ResponseEntity<List<Product>> getProductsBySupplier(@PathVariable long idSupplier) {
        return new ResponseEntity<>(productService.getProductsBySupplier(idSupplier)
                , HttpStatus.OK);
    }

}
