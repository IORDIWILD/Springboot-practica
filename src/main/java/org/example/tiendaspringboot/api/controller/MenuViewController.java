package org.example.tiendaspringboot.api.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MenuViewController {

    @GetMapping("/")
    public String mostrarMenuPrincipal(){
        return "index";
    }

}
