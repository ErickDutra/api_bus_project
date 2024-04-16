package com.br.bussticket.busticket.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.br.bussticket.busticket.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class NoAuthenticationController {
    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
   
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
    

    @GetMapping("/main")
    public String mainPage(HttpSession session) {
    
        return "main";
    }

}
