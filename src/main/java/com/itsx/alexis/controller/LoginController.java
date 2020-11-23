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

    @GetMapping("/login")
    public String getLogin(Model model) {
        Login user = new Login();

        model.addAttribute("loginUser",user);
        return "login";
    }

    @PostMapping("/validate/admin")
    public String validateAdmin(Login loginUser) {
        List<Administrator> administrators = administratorService.findAll();

        if (isValidateAdmin(administrators,loginUser)) {
            return "redirect:/admin/management/"+loginUser.getUserName()+"/"+loginUser.getPassword();
        } else {
            return "redirect:/login";
        }
    }

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
