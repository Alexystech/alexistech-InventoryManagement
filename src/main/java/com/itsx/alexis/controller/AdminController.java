package com.itsx.alexis.controller;

import com.itsx.alexis.entity.Administrator;
import com.itsx.alexis.entity.Category;
import com.itsx.alexis.entity.Product;
import com.itsx.alexis.entity.Supplier;
import com.itsx.alexis.service.AdministratorService;
import com.itsx.alexis.service.CategoryService;
import com.itsx.alexis.service.ProductService;
import com.itsx.alexis.service.SupplierService;
import com.itsx.alexis.utility.CategoryUtility;
import com.itsx.alexis.utility.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

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
    private int idAdmin;

    /**
     * Este endpoint mostrara el dashboard del administrador
     * desde este punto se tiene acceso a todas las acciones
     * disponibles de la aplicacion.
     *
     * @param userName
     * @param password
     * @param model
     * @return
     */
    @GetMapping("/admin/management/{userName}/{password}")
    public String getRegister(@PathVariable("userName") String userName, @PathVariable("password") String password, Model model) {

        this.userName = userName;
        this.password = password;
        this.idAdmin = getAdministrator(administratorService.findAll(), userName).getId();
        Product product;
        Category category;
        Supplier supplier;

        Administrator administrator = getAdministrator(
                administratorService.findAll(),
                userName
        );

        model.addAttribute("administrator", administrator);
        model.addAttribute("product", new Product());
        model.addAttribute("category", new Category());
        model.addAttribute("supplier", new Supplier());

        model.addAttribute("administrators", administratorService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());

        Map<String, Integer> ss = new LinkedHashMap<>();
        ss.put("Java",40);
        ss.put("Javascript",10);
        ss.put("Laravel",15);

        model.addAttribute("map",ss);

        return "management";
    }

    /**
     * Endpoint donde se almacena en la base de datos el producto.
     *
     * @param product
     * @return
     */
    @PostMapping("/auth/save/product")
    public String saveProduct(@Validated Product product) {
        product.setAdministrator(administratorService.findById(idAdmin).get());
        productService.createProduct(product);
        return "redirect:/admin/management/" + this.userName + "/" + this.password;
    }

    /**
     * Endpoint donde se almacena en la base de datos la categoria.
     *
     * @param category
     * @return
     */
    @PostMapping("/auth/save/category")
    private String saveCategory(@Validated Category category) {
        categoryService.createCategory(category);
        return "redirect:/admin/management/" + this.userName + "/" + this.password;
    }

    /**
     * Endpoint donde se almacena en la base de datos en proveedor.
     *
     * @param supplier
     * @return
     */
    @PostMapping("/auth/save/supplier")
    public String saveSupplier(@Validated Supplier supplier) {
        supplierService.createSupplier(supplier);
        return "redirect:/admin/management/" + this.userName + "/" + this.password;
    }

    //Endpoints administrativos

    /**
     * @param model
     * @return
     */
    @GetMapping("/management/stock")
    public String getStockViewer(Model model) {
        List<Category> categories = categoryService.findAll();

        model.addAttribute("categories", categories);
        model.addAttribute("categoryUtility", new CategoryUtility());

        return "stockviewer";
    }

    @PostMapping("/management/stock/by/category")
    public String postStockViewer(CategoryUtility categoryUtility, Model model) {

        List<Category> categories = categoryService.findAll();

        if (categoryUtility.getId() != 0) {
            Optional<Category> category = categoryService.findById(categoryUtility.getId());

            model.addAttribute("filteredProducts", filteredProducts(category
                    .get()
                    .getIdCategory()
            )); //filtrar productos por categorias
        }

        model.addAttribute("categoryUtility", new CategoryUtility());
        model.addAttribute("categories", categories);

        return "stockviewer";
    }

    @GetMapping("/switch/admin")
    public String getSwitch() {
        return "redirect:/admin/management/" + userName + "/" + password;
    }

    @GetMapping("/product/update/{idProduct}")
    public String getUpdate(@PathVariable int idProduct, Model model) {
        Optional<Product> product = productService.findById(idProduct);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("product", product.get());
        return "updateFormProduct";
    }

    @PostMapping("/auth/update/product")
    public String postUpdateProduct(@Validated Product product) {
        product.setAdministrator(administratorService.findById(idAdmin).get());
        productService.createProduct(product);
        return "redirect:/management/stock";
    }

    @GetMapping("/product/delete/{idProduct}")
    public String deleteProduct(@PathVariable int idProduct) {
        productService.removeProduct(idProduct);
        return "redirect:/management/stock";
    }

    /**
     * Categorias endpoints
     *
     * @param model
     * @return
     */
    @GetMapping("/management/categories")
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("validation", new Validation());
        return "categoriesviewer";
    }

    @GetMapping("/category/update/{idCategory}")
    public String getUpdateCategory(@PathVariable int idCategory, Model model) {
        Optional<Category> category = categoryService.findById(idCategory);
        model.addAttribute("category", category.get());
        return "updateFormCategory";
    }

    @PostMapping("/auth/update/category")
    public String postUpdateCategory(@Validated Category category) {
        categoryService.createCategory(category);
        return "redirect:/management/categories";
    }

    @GetMapping("/category/delete/{idCategory}")
    public String deleteCategory(@PathVariable int idCategory) {
        categoryService.deleteCategory(idCategory);
        return "redirect:/management/categories";
    }

    /**
     * suppliers endpoints
     *
     * @param model
     * @return
     */
    @GetMapping("/management/suppliers")
    public String getSuppliers(Model model) {
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("validation", new Validation());
        return "supplierviewer";
    }

    @GetMapping("/supplier/update/{idSupplier}")
    public String getSupplier(@PathVariable int idSupplier, Model model) {
        Optional<Supplier> supplier = supplierService.findById(idSupplier);
        model.addAttribute("supplier", supplier.get());
        return "updateFormSupplier";
    }

    @PostMapping("/auth/update/supplier")
    public String postUpdateSupplier(@Validated Supplier supplier) {
        supplierService.createSupplier(supplier);
        return "redirect:/management/suppliers";
    }

    @GetMapping("/supplier/delete/{idSupplier}")
    public String deleteSupplier(@PathVariable int idSupplier) {
        supplierService.removeSupplier(idSupplier);
        return "redirect:/management/suppliers";
    }

    /**
     * Queda pendiente recuerda que la clave para resolverlo puede ser crear una clase para esto
     */
    private boolean haveAnyProduct() {
        List<Product> products = productService.findAll();
        if (products.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private List<Product> filteredProducts(int id) {
        List<Product> list = new LinkedList<>();
        List<Product> products = productService.findAll();
        for (Product product : products) {
            if (product.getCategory().getIdCategory() == id) {
                list.add(product);
            }
        }
        return list;
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
