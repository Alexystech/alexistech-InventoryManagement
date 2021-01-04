package com.itsx.alexis.controller;

import com.itsx.alexis.entity.Administrator;
import com.itsx.alexis.service.AdministratorService;
import com.itsx.alexis.utility.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    AdministratorService administratorService;

    /**
     * Endpoint que muestra el formulario de registro
     *
     * @param model
     * @return
     */
    @GetMapping("/register/admin")
    public String getRegister(Model model) {
        Administrator administrator;

        model.addAttribute("administrator", new Administrator());

        return "register";
    }

    /**
     * Endpoint para validar el registro
     * <p>
     * Si el metodo {@methot isValidateRegister} valida
     * correctamente el registro se redirecciona al
     * endpoint {@code /index} de lo contrario la redireccion es
     * al endpoint {@code /validation/register}.
     *
     * @param administrator
     * @param model
     * @return
     */
    @PostMapping("/validation/register")
    public String validateRegister(@Valid Administrator administrator, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("administrator",administrator);
            return "register";
        }
        Register utilityRegister = new Register();

        if (utilityRegister.isValidateRegister(administratorService
                .findAll(), administrator)) {
            administratorService.createAdministrator(administrator);
        } else {
            return "redirect:/register/admin";
        }
        return "redirect:/index";
    }
}
