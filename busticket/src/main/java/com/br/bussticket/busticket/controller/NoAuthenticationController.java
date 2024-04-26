package com.br.bussticket.busticket.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.bussticket.busticket.model.TripTable;
import com.br.bussticket.busticket.repository.TripRepository;


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

    @Autowired
    private TripRepository tripTableRepository;

    @PostMapping("/trip")
    @ResponseBody
    public String createTrip(@RequestBody TripTable newTrip) {
        tripTableRepository.save(newTrip);
        return "Viagem criada com sucesso!";
    }
    

}
