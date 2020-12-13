package com.itsx.alexis.controller;

import com.itsx.alexis.entity.Administrator;
import com.itsx.alexis.service.AdministratorService;
import com.itsx.alexis.utility.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    AdministratorService administratorService;

    /**
     * Endpoint principal donde se encuentra el
     * login de la web app.
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String getLogin(Model model) {
        Login user = new Login();

        model.addAttribute("loginUser",user);
        return "index";
    }

    /**
     * en este endpoint se valida si el login es correcto o no
     * si el metodo {@methot isValidateAdmin} valida correctamente al
     * usuario la redireccion es al endpoint {@code /admin/management/loginUser/password}
     * de lo contrario la redireccion es para el endpoint {@code /index}.
     * @param loginUser
     * @return
     */
    @PostMapping("/validate/admin")
    public String validateAdmin(Login loginUser) {
        List<Administrator> administrators = administratorService.findAll();

        if (isValidateAdmin(administrators,loginUser)) {
            return "redirect:/admin/management/"+loginUser.getUserName()+"/"+loginUser.getPassword();
        } else {
            return "redirect:/index";
        }
    }

    /**
     * Metodo para validar el administrador para logeo
     * @param administrators
     * @param loginUser
     * @return
     */
    public boolean isValidateAdmin(List<Administrator> administrators, Login loginUser) {
        boolean definition = false;

        for (Administrator administrator : administrators) {
            if (administrator.getUserName().equals(loginUser.getUserName()) &&
                    administrator.getPassword().equals(loginUser.getPassword())) {
                definition = true;
                break;
            }
        }
        return definition;
    }
}
