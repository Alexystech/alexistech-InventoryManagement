package com.itsx.alexis.restapi;

import com.itsx.alexis.entity.Administrator;
import com.itsx.alexis.service.AdministratorService;
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
@RequestMapping(path = "/administrator")
public class AdministratorRestController {

    private final AdministratorService administratorService;

    @Autowired
    public AdministratorRestController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @ApiResponses({
            @ApiResponse(code = 201, message = "created"),
            @ApiResponse(code = 422, message = "administrator is null, unprocessable entity")
    })
    @PostMapping(path = "/create")
    public ResponseEntity<Administrator> createAdministrator(@RequestBody Administrator administrator) {
        return new ResponseEntity<>(administratorService.createAdministrator(administrator)
                , HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(code = 202, message = "id accepted, administrator was been deleted"),
            @ApiResponse(code = 404, message = "administrator was not found"),
            @ApiResponse(code = 422, message = "id out of bound, your id is minor to 1")
    })
    @DeleteMapping(path = "/delete/{idAdministrator}")
    public ResponseEntity<Boolean> deleteAdministratorById(@PathVariable long idAdministrator) {
        administratorService.deleteAdministratorById(idAdministrator);
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }

    @ApiResponses({
            @ApiResponse(code = 202, message = "updated"),
            @ApiResponse(code = 422, message = "administrator is null, unprocessable entity")
    })
    @PutMapping(path = "/update")
    public ResponseEntity<Administrator> updateAdministrator(@RequestBody Administrator administrator) {
        return new ResponseEntity<>(administratorService.updateAdministrator(administrator)
                , HttpStatus.ACCEPTED);
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "the administrator was returned"),
            @ApiResponse(code = 404, message = "administrator was not found, try with other id")
    })
    @GetMapping(path = "/get/{idAdministrator}")
    public ResponseEntity<Administrator> getAdministratorById(@PathVariable long idAdministrator) {
        return new ResponseEntity<>(administratorService.getAdministratorById(idAdministrator)
                , HttpStatus.OK);
    }

    @ApiResponse(code = 200, message = "All the administrators was returned")
    @GetMapping(path = "/get/all")
    public ResponseEntity<List<Administrator>> getAllAdministrators() {
        return new ResponseEntity<>(administratorService.getAllAdministrators(), HttpStatus.OK);
    }

}
