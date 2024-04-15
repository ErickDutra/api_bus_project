package com.br.bussticket.busticket.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class PagesController {
    @GetMapping("/home")
    public String exibirHome() {
        return "home";
    }

    @GetMapping("/login")
    public String exibirLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String exibirRegister() {
        return "register";
    }
}
