package com.itsx.alexis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }
}
