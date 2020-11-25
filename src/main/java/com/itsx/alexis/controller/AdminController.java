package com.itsx.alexis.controller;

import com.itsx.alexis.entity.Administrator;
import com.itsx.alexis.entity.Category;
import com.itsx.alexis.entity.Product;
import com.itsx.alexis.entity.Supplier;
import com.itsx.alexis.service.AdministratorService;
import com.itsx.alexis.service.CategoryService;
import com.itsx.alexis.service.ProductService;
import com.itsx.alexis.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SupplierService supplierService;

    private String userName;
    private String password;

    @GetMapping("/admin/management/{userName}/{password}")
    public String postRegister(@PathVariable("userName") String userName,@PathVariable("password") String password, Model model) {

        this.userName = userName;
        this.password = password;
        Product product;
        Category category;
        Supplier supplier;

        Administrator administrator = getAdministrator(
                administratorService.findAll(),
                userName
        );

        model.addAttribute("administrator",administrator);
        model.addAttribute("product",new Product());
        model.addAttribute("category",new Category());
        model.addAttribute("supplier",new Supplier());

        model.addAttribute("administrators",administratorService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("products",productService.findAll());
        model.addAttribute("suppliers",supplierService.findAll());

        return "management";
    }

    @PostMapping("/auth/save/product")
    public String saveProduct(Product product) {
        productService.createProduct(product);
        return "redirect:/admin/management/"+this.userName+"/"+this.password;
    }

    @PostMapping("/auth/save/category")
    private String saveCategory(Category category) {
        categoryService.createCategory(category);
        return "redirect:/admin/management/"+this.userName+"/"+this.password;
    }

    @PostMapping("/auth/save/supplier")
    public String saveSupplier(Supplier supplier) {
        supplierService.createSupplier(supplier);
        return "redirect:/admin/management/"+this.userName+"/"+this.password;
    }

    public Administrator getAdministrator(List<Administrator> administrators, String userName) {
        Administrator aux = null;
        for (Administrator administrator : administrators) {
            if (administrator.getUserName().equals(userName)) {
                aux = administrator;
                break;
            }
        }
        return aux;
    }
}
