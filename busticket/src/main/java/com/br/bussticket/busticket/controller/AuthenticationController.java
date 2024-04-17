package com.br.bussticket.busticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import com.br.bussticket.busticket.dto.AuthenticationDTO;
import com.br.bussticket.busticket.dto.RegisterDTO;
import com.br.bussticket.busticket.dto.UsuarioDTO;
import com.br.bussticket.busticket.model.User;
import com.br.bussticket.busticket.repository.UserRepository;
import com.br.bussticket.busticket.security.TokenService;
import com.br.bussticket.busticket.service.UserService;


@Controller
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
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        User user = this.userService.getUserPorEmail(data.email());
        if (user.getId_user() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);
            UsuarioDTO usuarioDTO = new UsuarioDTO(user.getName(), user.getEmail(), user.getCpf(), token);
            return new ResponseEntity<>(usuarioDTO, headers, HttpStatus.OK);
        } else {
            // Se o usuário não for encontrado, retorna uma resposta de erro
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
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

    public String mainPage(HttpServletRequest request, HttpSession session) {
        // Recupera o token do cabeçalho 'Authorization'
        String authHeader = request.getHeader("Authorization");
    
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            String token = authHeader.substring(7); // Remove o prefixo 'Bearer '
    
            // Valida e decodifica o token para obter as informações do usuário
            String email = tokenService.validateToken(token);
            User user = repository.findByEmail(email);
    
            // Se o usuário existir, você pode autenticá-lo como preferir
            if (user != null) {
                // Autentica o usuário
                // Exemplo de autenticação: definindo o usuário autenticado no contexto de segurança
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
    
                // Armazena o usuário na sessão
                session.setAttribute("user", user);
    
                // Redireciona para a página principal
                return "main"; // Nome da página principal
            }
        }
    
        // Em caso de token inválido ou usuário não encontrado, redirecione para a página de login
        return "redirect:/login"; // Redireciona para a página de login
    }
}
