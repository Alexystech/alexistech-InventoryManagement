package com.itsx.alexis.restapi;

import com.itsx.alexis.entity.Administrator;
import com.itsx.alexis.service.AdministratorService;
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
@RequestMapping(path = "/administrator")
public class AdministratorRestController {

    private final AdministratorService administratorService;

    @Autowired
    public AdministratorRestController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Administrator> createAdministrator(@RequestBody Administrator administrator) {
        return new ResponseEntity<>(administratorService.createAdministrator(administrator)
                , HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/delete/{idAdministrator}")
    public ResponseEntity<Boolean> deleteAdministratorById(@PathVariable long idAdministrator) {
        administratorService.deleteAdministratorById(idAdministrator);
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Administrator> updateAdministrator(@RequestBody Administrator administrator) {
        return new ResponseEntity<>(administratorService.updateAdministrator(administrator)
                , HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/get/{idAdministrator}")
    public ResponseEntity<Administrator> getAdministratorById(@PathVariable long idAdministrator) {
        return new ResponseEntity<>(administratorService.getAdministratorById(idAdministrator)
                , HttpStatus.OK);
    }

    @GetMapping(path = "/get/all")
    public ResponseEntity<List<Administrator>> getAllAdministrators() {
        return new ResponseEntity<>(administratorService.getAllAdministrators(), HttpStatus.OK);
    }

}
