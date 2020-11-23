package com.itsx.alexis.controller;

import com.itsx.alexis.entity.Administrator;
import com.itsx.alexis.service.AdministratorService;
import com.itsx.alexis.utility.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    AdministratorService administratorService;

    @GetMapping("/register/admin")
    public String getRegister(Model model) {
        Administrator administrator;

        model.addAttribute("administrator", new Administrator());

        return "register";
    }

    @PostMapping("/validation/register")
    public String validateRegister(Administrator administrator, Model model) {
        Register utilityRegister = new Register();

        if (utilityRegister.isValidateRegister(administratorService
                .findAll(),administrator)) {
            administratorService.createAdministrator(administrator);
        } else {
            return "redirect:/register/admin";
        }
        return "redirect:/login";
    }
}
