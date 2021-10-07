package com.itsx.alexis.restapi;

import com.itsx.alexis.entity.Supplier;
import com.itsx.alexis.service.SupplierService;
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
@RequestMapping(path = "/supplier")
public class SupplierRestController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierRestController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier) {
        return new ResponseEntity<>(supplierService.createSupplier(supplier)
                , HttpStatus.CREATED);
    }

    @PostMapping(path = "/create/pull")
    public ResponseEntity<List<Supplier>> createAPullOfSuppliers(@RequestBody List<Supplier> suppliers) {
        return new ResponseEntity<>(supplierService.createAPullOfSuppliers(suppliers)
                , HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{idSupplier}")
    public ResponseEntity<Boolean> deleteSupplierById(@PathVariable long idSupplier) {
        supplierService.deleteSupplierById(idSupplier);
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Supplier> updateSupplier(@RequestBody Supplier supplier) {
        return new ResponseEntity<>(supplierService.updateSupplier(supplier)
                , HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/get/{idSupplier}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable long idSupplier) {
        return new ResponseEntity<>(supplierService.getSupplierById(idSupplier)
                , HttpStatus.OK);
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        return new ResponseEntity<>(supplierService.getAllSuppliers()
                , HttpStatus.OK);
    }

}
