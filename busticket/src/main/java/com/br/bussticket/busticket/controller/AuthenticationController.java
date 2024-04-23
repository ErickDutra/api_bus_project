package com.br.bussticket.busticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import com.br.bussticket.busticket.dto.AuthenticationDTO;
import com.br.bussticket.busticket.dto.LoginResponseDTO;
import com.br.bussticket.busticket.dto.RegisterDTO;
import com.br.bussticket.busticket.model.User;
import com.br.bussticket.busticket.repository.UserRepository;
import com.br.bussticket.busticket.security.TokenService;
import com.br.bussticket.busticket.service.UserService;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        System.out.println(token);

        return new LoginResponseDTO(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.cpf(), data.name(), data.email(), encryptedPassword, data.role());
        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/main")
    public ModelAndView mainPage(@RequestHeader("Authorization") String authorization) {
    
        System.out.println(authorization);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("Token invalido");
            return new ModelAndView("login");
        }

        // https://www.baeldung.com/slf4j-with-log4j2-logback

        String token = authHeader.substring(7);
        System.out.println("Token " + token);

        String email = tokenService.validateToken(token);
        User user = repository.findByEmail(email);

        if (user == null) {
            System.out.println("Usuario invalido");
            return new ModelAndView("login");
        }

        return new ModelAndView("main");
    }
}