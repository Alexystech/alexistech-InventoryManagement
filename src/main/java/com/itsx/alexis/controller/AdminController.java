package com.itsx.alexis.controller;

import com.itsx.alexis.entity.Administrator;
import com.itsx.alexis.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    AdministratorService administratorService;

    @GetMapping("/admin/management/{userName}/{password}")
    public String postRegister(@PathVariable("userName") String userName,@PathVariable("password") String password, Model model) {

        Administrator administrator = getAdministrator(
                administratorService.findAll(),
                userName
        );

        model.addAttribute("administrator",administrator);

        return "management";
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
